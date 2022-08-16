package ru.bogdanov.tindortest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.bogdanov.tindortest.model.Message;
import ru.bogdanov.tindortest.service.MessageService;

import java.net.URI;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Message> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(messageService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Message> save(@RequestBody Message message) {
        Message messageCreated = messageService.save(message);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(messageCreated.getId())
                .toUri();

        return ResponseEntity.created(uri).body(messageCreated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        messageService.delete(id);
    }
}
