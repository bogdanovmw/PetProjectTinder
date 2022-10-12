package ru.bogdanov.tindortest.securituserivce.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.bogdanov.tindortest.securituserivce.service.UserServiceAuth;
import ru.bogdanov.tindortest.userservice.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserServiceAuth userServiceAuth;

    public UserDetailsServiceImpl(UserServiceAuth userServiceAuth) {
        this.userServiceAuth = userServiceAuth;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userServiceAuth.findByName(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return UserDetailsImpl.build(user);
    }
}
