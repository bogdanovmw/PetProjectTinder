package ru.bogdanov.tindortest.userservice.exception;

public class UserNotFoundException extends RuntimeException  {
    public UserNotFoundException(long id) {
      super("User by id: " + id + " not found!");
    }
}
