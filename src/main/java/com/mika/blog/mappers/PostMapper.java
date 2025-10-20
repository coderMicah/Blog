package com.mika.blog.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.mika.blog.domain.CreatePostRequest;
import com.mika.blog.domain.UpdatePostRequest;
import com.mika.blog.domain.dtos.CreatePostRequestDto;
import com.mika.blog.domain.dtos.PostDto;
import com.mika.blog.domain.dtos.UpdatePostRequestDto;
import com.mika.blog.domain.entities.Post;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "author", source = "author")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "tags", source = "tags")
    PostDto toDto(Post post);

    CreatePostRequest toCreatePostRequest(CreatePostRequestDto dto);

    UpdatePostRequest toUpdatePostRequest(UpdatePostRequestDto dto);
}
