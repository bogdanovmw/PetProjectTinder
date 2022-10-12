package ru.bogdanov.tindortest.securituserivce.service;

import ru.bogdanov.tindortest.securituserivce.model.Role;
import ru.bogdanov.tindortest.userservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceAuth {
    String loginUser(String name, String password);
    User registerUser(User user);
    List<User> findAll();
    Optional<User> findByName(String name);
    User findById(Long id);
}
