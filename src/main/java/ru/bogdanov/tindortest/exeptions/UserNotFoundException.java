package ru.bogdanov.tindortest.exeptions;

public class UserNotFoundException extends RuntimeException  {
    public UserNotFoundException(long id) {
      super("User by id: " + id + " not found!");
    }
}
