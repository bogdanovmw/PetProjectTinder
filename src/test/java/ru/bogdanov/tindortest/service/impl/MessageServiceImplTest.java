package ru.bogdanov.tindortest.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.bogdanov.tindortest.chatservice.model.Message;
import ru.bogdanov.tindortest.chatservice.repository.MessageRepository;
import ru.bogdanov.tindortest.chatservice.service.impl.MessageServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageServiceImplTest {
    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageServiceImpl messageService;

    private Message message = Message.builder()
                .id(1)
                .senderId("1")
                .recipientId("2")
                .addedAt("16.08.2022 18:00")
                .content("Hi, Anna!")
            .build();


    @Test
    void findByIdTest() {
        when(messageRepository.findById(message.getId())).thenReturn(Optional.of(message));

        Message expected = messageService.findById(message.getId());
        assertThat(expected).isSameAs(message);

        verify(messageRepository).findById(message.getId());
    }

    @Test
    void saveTest() {
        when(messageRepository.save(any(Message.class))).thenReturn(message);

        Message expected = messageService.save(message);
        assertThat(expected).isSameAs(message);

        verify(messageRepository).save(message);
    }

    @Test
    void deleteTest() {
        when(messageRepository.findById(message.getId())).thenReturn(Optional.of(message));

        messageService.delete(message.getId());

        verify(messageRepository).findById(message.getId());
        verify(messageRepository).deleteById(message.getId());
    }
}