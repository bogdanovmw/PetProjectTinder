package ru.bogdanov.tindortest.service.impl;

import org.springframework.stereotype.Service;
import ru.bogdanov.tindortest.exeptions.MessageNotFoundException;
import ru.bogdanov.tindortest.model.Message;
import ru.bogdanov.tindortest.repository.MessageRepository;
import ru.bogdanov.tindortest.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message findById(Long id) {
        return messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void delete(long id) {
        messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
        messageRepository.deleteById(id);
    }
}
