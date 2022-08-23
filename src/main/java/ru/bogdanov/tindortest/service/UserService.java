package ru.bogdanov.tindortest.service;

import ru.bogdanov.tindortest.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User save(User user);
    User savePhoto(Long id, String filename);
    void delete(long id);

    List<User> findAllByIdNot(Long id);
}
