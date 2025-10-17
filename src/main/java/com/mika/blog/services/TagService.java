package com.mika.blog.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.mika.blog.domain.entities.Tag;

public interface TagService {
    List<Tag> getAllTags();

    List<Tag> createTags(Set<String> tags);

    void deleteTag(UUID id);
}
