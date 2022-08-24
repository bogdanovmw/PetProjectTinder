package ru.bogdanov.tindortest.chatservice.exeptions;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(long id) {
        super("Message by id: " + id + " not found!");
    }
}
