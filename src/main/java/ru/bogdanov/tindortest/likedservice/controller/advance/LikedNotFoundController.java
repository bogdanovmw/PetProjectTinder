package ru.bogdanov.tindortest.likedservice.controller.advance;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.bogdanov.tindortest.likedservice.exeption.LikedNotFoundException;

@ControllerAdvice
public class LikedNotFoundController {
    @ResponseBody
    @ExceptionHandler(LikedNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String messageNotFound(LikedNotFoundException e) {
        return e.getMessage();
    }
}
