package com.mika.blog.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mika.blog.domain.PostStatus;
import com.mika.blog.domain.entities.Category;
import com.mika.blog.domain.entities.Post;
import com.mika.blog.domain.entities.Tag;
import com.mika.blog.domain.entities.User;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    List<Post> findAllByStatusAndCategoryAndTagsContaining(PostStatus status, Category category, Tag tag);

    List<Post> findAllByStatusAndCategory(PostStatus status, Category category);

    List<Post> findAllByStatusAndTags(PostStatus status, Tag tag);

    List<Post> findAllByStatus(PostStatus status);

    List<Post> findAllByAuthorAndStatus(User author, PostStatus status);

}
