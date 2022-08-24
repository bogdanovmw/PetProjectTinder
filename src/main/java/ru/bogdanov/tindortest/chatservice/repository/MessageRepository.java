package ru.bogdanov.tindortest.chatservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bogdanov.tindortest.chatservice.model.Message;
import ru.bogdanov.tindortest.chatservice.model.MessageStatus;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    long countBySenderIdAndRecipientIdAndStatus(String senderId, String recipientId, MessageStatus status);
    List<Message> findByChatId(String id);
    List<Message> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
