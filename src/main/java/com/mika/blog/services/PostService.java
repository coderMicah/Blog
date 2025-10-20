package com.mika.blog.services;

import java.util.List;
import java.util.UUID;

import com.mika.blog.domain.CreatePostRequest;
import com.mika.blog.domain.UpdatePostRequest;
import com.mika.blog.domain.entities.Post;
import com.mika.blog.domain.entities.User;

public interface PostService {
    Post getPostById(UUID id);

    List<Post> getAllPosts(UUID categoryId, UUID tagId);

    List<Post> getDraftPosts(User user);

    Post createPost(User user, CreatePostRequest createPostRequest);

    Post updatePost(UUID id, UpdatePostRequest updatePostRequest);

    void deletePost(UUID id);

}
