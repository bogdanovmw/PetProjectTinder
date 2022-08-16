package ru.bogdanov.tindortest.service;

import ru.bogdanov.tindortest.model.Message;

public interface MessageService {
    Message findById(Long id);
    Message save(Message message);
    void delete(long id);
}
