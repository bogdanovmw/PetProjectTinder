package ru.bogdanov.tindortest.chatservice.service;

import ru.bogdanov.tindortest.chatservice.model.Message;

import java.util.List;

public interface MessageService {
    Message findById(Long id);
    Message save(Message message);
    long countNewMessages(String senderId, String recipientId);
    List<Message> findChatMessages(String senderId, String recipientId);
    void delete(long id);
}
