package com.mika.blog.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mika.blog.domain.entities.Tag;
import com.mika.blog.repositories.TagRepository;
import com.mika.blog.services.TagService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAllWithPostCount();
    }

    @Transactional
    @Override
    public List<Tag> createTags(Set<String> tagNames) {
        // Find already existing tags
        List<Tag> existingTags = tagRepository.findByNameIn(tagNames);
        Set<String> existingTagNames = existingTags.stream()
                .map(tag -> tag.getName())
                .collect(Collectors.toSet());

        // Prepare new tags
        List<Tag> newTags = tagNames.stream()
                .filter(name -> !existingTagNames.contains(name))
                .map(name -> Tag.builder().name(name).posts(new HashSet<>()).build())
                .collect(Collectors.toList());

        // Save new tags and capture the saved entities
        List<Tag> savedNewTags = new ArrayList<>();
        if (!newTags.isEmpty()) {
            savedNewTags = tagRepository.saveAll(newTags);
        }

        // Combine existing + newly saved tags
        List<Tag> allTags = new ArrayList<>();
        allTags.addAll(existingTags);
        allTags.addAll(savedNewTags);

        return allTags;
    }

    @Transactional
    @Override
    public void deleteTag(UUID id) {
        tagRepository.findById(id).ifPresent(tag -> {
            if (!tag.getPosts().isEmpty()) {
                throw new IllegalStateException("Cannot delete tag with posts");
            }
            tagRepository.deleteById(id);
        });
    }

    @Override
    public Tag getTagById(UUID id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tag not found with id: " + id));
    }

    @Override
    public List<Tag> getTagByIds(Set<UUID> ids) {
        List<Tag> foundTags = tagRepository.findAllById(ids);
        if (foundTags.size() != ids.size()) {
            throw new EntityNotFoundException("Not all specified tag id(s) exist");
        }
        return foundTags;
    }

}
