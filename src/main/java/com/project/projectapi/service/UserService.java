package com.project.projectapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.project.projectapi.entities.User;

public interface UserService {
    User createUser(User user);
    User saveUser(User user);
    User updateUser(String email, User user);
    void deleteUser(String email);
    Optional<User> getUserbyEmail(String email);
    List<User> getAllUsers();
    Page<User> getUsers(String email, int page, int size);
}
