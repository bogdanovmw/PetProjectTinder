package ru.bogdanov.tindortest.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;
import ru.bogdanov.tindortest.model.Liked;
import ru.bogdanov.tindortest.repository.LikedRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class LikedServiceImplTest {
    @Mock
    private LikedRepository likedRepository;

    @InjectMocks
    private LikedServiceImpl likedService;

    private Liked liked = Liked.builder()
                .id(1L)
                .userId(1)
                .userLikedId(2)
                .status(true)
            .build();

    @Test
    void findByIdTest() {
        when(likedRepository.findById(liked.getId())).thenReturn(Optional.of(liked));

        Liked expected = likedService.findById(liked.getId());
        assertThat(expected).isSameAs(liked);

        verify(likedRepository).findById(liked.getId());
    }

    @Test
    void saveTest() {
        when(likedRepository.save(any(Liked.class))).thenReturn(liked);

        Liked expected = likedService.save(liked);
        assertThat(expected.getId()).isSameAs(liked.getId());

        verify(likedRepository).save(liked);
    }

    @Test
    void deleteTest() {
        when(likedRepository.findById(liked.getId())).thenReturn(Optional.of(liked));

        likedService.delete(liked.getId());

        verify(likedRepository).deleteById(liked.getId());
    }
}