package com.mika.blog.domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Post> posts = new ArrayList<>();

    // --- Constructors ---
    public Category() {
    }

    public Category(UUID id, String name, List<Post> posts) {
        this.id = id;
        this.name = name;
        this.posts = posts != null ? posts : new ArrayList<>();
    }

    // --- Getters ---
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Post> getPosts() {
        return posts;
    }

    // --- Setters ---
    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    // --- Builder ---
    public static class Builder {
        private UUID id;
        private String name;
        private List<Post> posts = new ArrayList<>();

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder posts(List<Post> posts) {
            this.posts = posts;
            return this;
        }

        public Category build() {
            return new Category(id, name, posts);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    // --- equals & hashCode (exclude posts) ---
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Category category))
            return false;
        return Objects.equals(id, category.id) &&
                Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    // --- toString (exclude posts) ---
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
