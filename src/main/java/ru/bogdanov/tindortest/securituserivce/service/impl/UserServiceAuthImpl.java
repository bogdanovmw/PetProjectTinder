package ru.bogdanov.tindortest.securituserivce.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.bogdanov.tindortest.securituserivce.exception.EmailAlreadyExistsException;
import ru.bogdanov.tindortest.securituserivce.exception.UsernameAlreadyExistsException;
import ru.bogdanov.tindortest.securituserivce.model.ERole;
import ru.bogdanov.tindortest.securituserivce.model.Role;
import ru.bogdanov.tindortest.securituserivce.service.UserServiceAuth;
import ru.bogdanov.tindortest.userservice.exception.UserNotFoundException;
import ru.bogdanov.tindortest.userservice.model.User;
import ru.bogdanov.tindortest.userservice.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceAuthImpl implements UserServiceAuth {
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public UserServiceAuthImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String loginUser(String name, String password) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(name, password));
        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public User registerUser(User user) {
        if (userRepository.existsByName(user.getName()))
            throw new UsernameAlreadyExistsException(String.format("username %s already exists", user.getName()));
        if (userRepository.existsByEmail(user.getEmail()))
            throw new EmailAlreadyExistsException(String.format("email %s already exists", user.getEmail()));

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(new Role(ERole.ROLE_USER));
        user.setRoles(roles);

        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
