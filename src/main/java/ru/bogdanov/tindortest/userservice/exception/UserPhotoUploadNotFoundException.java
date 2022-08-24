package ru.bogdanov.tindortest.userservice.exception;

public class UserPhotoUploadNotFoundException extends RuntimeException {
    public UserPhotoUploadNotFoundException(Long id) {
        System.out.println("Photo not found, by user id: " + id + " !");
    }
}
