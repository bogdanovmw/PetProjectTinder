package ru.bogdanov.tindortest.chatservice.service;

import java.util.Optional;

public interface RoomService {
    Optional<String> getChatId(String senderId, String recipientId, boolean createIfNotExists);
}
