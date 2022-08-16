package ru.bogdanov.tindortest.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.bogdanov.tindortest.model.User;
import ru.bogdanov.tindortest.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    User user = User.builder()
                .id(1)
                .name("Max")
                .email("maxim@mail.ru")
                .password("Qwe123321!")
                .photo("Test")
            .build();

    @Test
    void findAllTest() {
        List<User> users = new ArrayList<>();
        users.add(user);
        given(userRepository.findAll()).willReturn(users);

        List<User> expected = userService.findAll();
        assertEquals(expected, users);

        verify(userRepository).findAll();
    }

    @Test
    void findByIdTest() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        User expected = userService.findById(user.getId());
        assertThat(expected).isSameAs(user);

        verify(userRepository).findById(user.getId());
    }

    @Test
    void saveTest() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        User expected = userService.save(user);
        assertThat(expected).isSameAs(user);

        verify(userRepository).save(user);
    }

    @Test
    void deleteTest() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        userService.delete(user.getId());

        verify(userRepository).findById(user.getId());
        verify(userRepository).deleteById(user.getId());
    }
}