package ru.bogdanov.tindortest.exeptions;

public class LikedNotFoundException extends RuntimeException {
    public LikedNotFoundException(long id) {
        super("Liked by id: " + id + " not found!");
    }
}
