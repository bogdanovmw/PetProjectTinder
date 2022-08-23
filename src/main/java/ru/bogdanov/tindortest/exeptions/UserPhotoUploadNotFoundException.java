package ru.bogdanov.tindortest.exeptions;

public class UserPhotoUploadNotFoundException extends RuntimeException {
    public UserPhotoUploadNotFoundException(Long id) {
        System.out.println("Photo not found, by user id: " + id + " !");
    }
}
