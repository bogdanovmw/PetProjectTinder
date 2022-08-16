package ru.bogdanov.tindortest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.bogdanov.tindortest.model.Liked;
import ru.bogdanov.tindortest.service.LikedService;

import java.net.URI;

@RestController
@RequestMapping("/api/liked")
public class LikedController {
    private final LikedService likedService;

    public LikedController(LikedService likedService) {
        this.likedService = likedService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Liked> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(likedService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Liked> save(@RequestBody Liked liked) {
        Liked likedCreated = likedService.save(liked);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(likedCreated.getId())
                .toUri();

        return ResponseEntity.created(uri).body(likedCreated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        likedService.delete(id);
    }
}
