package ru.bogdanov.tindortest.chatservice.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import ru.bogdanov.tindortest.chatservice.model.ChatNotification;
import ru.bogdanov.tindortest.chatservice.model.Message;
import ru.bogdanov.tindortest.chatservice.service.MessageService;
import ru.bogdanov.tindortest.chatservice.service.RoomService;

@RestController
public class WebSocketMessageController {
    private final MessageService messageService;
    private final RoomService roomService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public WebSocketMessageController(MessageService messageService, RoomService roomService, SimpMessagingTemplate simpMessagingTemplate) {
        this.messageService = messageService;
        this.roomService = roomService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/chat")
    public void processMessage(@Payload Message message) {
        String room = roomService.getChatId(message.getSenderId(), message.getRecipientId(), true).get();
        message.setChatId(room);
        Message messageCreated = messageService.save(message);

        simpMessagingTemplate.convertAndSendToUser(
                message.getRecipientId(),
                "/queue/messages",
                new ChatNotification(messageCreated.getId(), messageCreated.getSenderId(), messageCreated.getContent())
        );
    }
}
