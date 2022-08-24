package ru.bogdanov.tindortest.chatservice.service.impl;

import org.springframework.stereotype.Service;
import ru.bogdanov.tindortest.chatservice.model.Room;
import ru.bogdanov.tindortest.chatservice.repository.RoomRepository;
import ru.bogdanov.tindortest.chatservice.service.RoomService;

import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Optional<String> getChatId(String senderId, String recipientId, boolean createIfNotExists) {
        return roomRepository.findBySenderIdAndRecipientId(senderId, recipientId)
                .map(Room::getChatId).or(() -> {
                    if (!createIfNotExists)
                        return Optional.empty();

                    String chatId = String.format("%s_%s", senderId, recipientId);
                    Room senderRecipient = Room.builder()
                                .chatId(chatId)
                                .senderId(senderId)
                                .recipientId(recipientId)
                            .build();

                    Room recipientSender = Room.builder()
                                .chatId(chatId)
                                .senderId(recipientId)
                                .recipientId(senderId)
                            .build();

                    roomRepository.save(senderRecipient);
                    roomRepository.save(recipientSender);

                    return Optional.of(chatId);
                });
    }
}
