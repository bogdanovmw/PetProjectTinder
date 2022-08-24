package ru.bogdanov.tindortest.chatservice.service.impl;

import org.springframework.stereotype.Service;
import ru.bogdanov.tindortest.chatservice.exeptions.MessageNotFoundException;
import ru.bogdanov.tindortest.chatservice.model.Message;
import ru.bogdanov.tindortest.chatservice.model.MessageStatus;
import ru.bogdanov.tindortest.chatservice.model.Room;
import ru.bogdanov.tindortest.chatservice.repository.MessageRepository;
import ru.bogdanov.tindortest.chatservice.repository.RoomRepository;
import ru.bogdanov.tindortest.chatservice.service.MessageService;
import ru.bogdanov.tindortest.chatservice.service.RoomService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final RoomService roomService;

    public MessageServiceImpl(MessageRepository messageRepository, RoomService roomService) {
        this.messageRepository = messageRepository;
        this.roomService = roomService;
    }

    @Override
    public Message findById(Long id) {
        return messageRepository
                .findById(id)
                .map(chatMessage -> {
                    chatMessage.setStatus(MessageStatus.DELIVERED);
                    return messageRepository.save(chatMessage);
                })
                .orElseThrow(() -> new MessageNotFoundException(id));
    }

    @Override
    public Message save(Message message) {
        message.setStatus(MessageStatus.RECEIVED);
        return messageRepository.save(message);
    }

    @Override
    public long countNewMessages(String senderId, String recipientId) {
        return messageRepository.countBySenderIdAndRecipientIdAndStatus(senderId, recipientId, MessageStatus.RECEIVED);
    }

    @Override
    public List<Message> findChatMessages(String senderId, String recipientId) {
        Optional<String> chatId = roomService.getChatId(senderId, recipientId, false);
        List<Message> messages = chatId.map(messageRepository::findByChatId).orElse(new ArrayList<>());

        if (!messages.isEmpty())
            updateStatus(senderId, recipientId, MessageStatus.DELIVERED);

        return messages;
    }

    private void updateStatus(String senderId, String recipientId, MessageStatus status) {
        List<Message> messages = messageRepository.findBySenderIdAndRecipientId(senderId, recipientId);
        messages.forEach(message -> message.setStatus(status));
        messageRepository.saveAll(messages);
    }

    @Override
    public void delete(long id) {
        messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
        messageRepository.deleteById(id);
    }
}
