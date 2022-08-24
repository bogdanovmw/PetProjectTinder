package ru.bogdanov.tindortest.userservice.controller.advance;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.bogdanov.tindortest.userservice.exception.UserNotFoundException;

@ControllerAdvice
public class UserNotFoundController {
    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotFount(UserNotFoundException e) {
        return e.getMessage();
    }
}
