package ru.bogdanov.tindortest.service;

import ru.bogdanov.tindortest.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User save(User user);
    void delete(long id);
}
