package com.mika.blog.domain.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Post> posts = new HashSet<>();

    // --- Constructors ---
    public Tag() {
    }

    public Tag(UUID id, String name, Set<Post> posts) {
        this.id = id;
        this.name = name;
        this.posts = posts != null ? posts : new HashSet<>();
    }

    // --- Getters ---
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    // --- Setters ---
    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    // --- Relationship helpers ---

    // --- Builder ---
    public static class Builder {
        private UUID id;
        private String name;
        private Set<Post> posts = new HashSet<>();

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder posts(Set<Post> posts) {
            this.posts = posts;
            return this;
        }

        public Tag build() {
            return new Tag(id, name, posts);
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
        if (!(o instanceof Tag tag))
            return false;
        return Objects.equals(id, tag.id) &&
                Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    // --- toString (exclude posts) ---
    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
