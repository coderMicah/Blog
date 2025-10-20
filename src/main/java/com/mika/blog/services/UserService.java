package com.mika.blog.services;

import java.util.UUID;

import com.mika.blog.domain.entities.User;

public interface UserService {
    User getUserById(UUID id);
}
