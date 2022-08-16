package ru.bogdanov.tindortest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.bogdanov.tindortest.model.User;
import ru.bogdanov.tindortest.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> save(@RequestBody User user) {
        User userCreated = userService.save(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();

        return ResponseEntity.created(uri).body(userCreated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

}
