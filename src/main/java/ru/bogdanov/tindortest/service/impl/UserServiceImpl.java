package ru.bogdanov.tindortest.service.impl;

import org.springframework.stereotype.Service;
import ru.bogdanov.tindortest.exeptions.UserNotFoundException;
import ru.bogdanov.tindortest.model.User;
import ru.bogdanov.tindortest.repository.UserRepository;
import ru.bogdanov.tindortest.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User savePhoto(Long id, String filename) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        user.setPhoto(filename);

        userRepository.save(user);

        return user;
    }

    @Override
    public void delete(long id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAllByIdNot(Long id) {
        return userRepository.findAllByIdNot(id);
    }
}
