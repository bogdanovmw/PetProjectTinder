package ru.bogdanov.tindortest.controller.advance;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.bogdanov.tindortest.exeptions.MessageNotFoundException;

@ControllerAdvice
public class MessageNotFoundController {
    @ResponseBody
    @ExceptionHandler(MessageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String messageNotFound(MessageNotFoundException e) {
        return e.getMessage();
    }
}
