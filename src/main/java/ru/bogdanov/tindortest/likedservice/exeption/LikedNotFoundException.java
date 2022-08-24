package ru.bogdanov.tindortest.likedservice.exeption;

public class LikedNotFoundException extends RuntimeException {
    public LikedNotFoundException(long id) {
        super("Liked by id: " + id + " not found!");
    }
}
