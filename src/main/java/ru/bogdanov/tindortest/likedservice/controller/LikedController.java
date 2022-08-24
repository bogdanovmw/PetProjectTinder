package ru.bogdanov.tindortest.likedservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.bogdanov.tindortest.likedservice.dto.LikedResponse;
import ru.bogdanov.tindortest.likedservice.exeption.LikedNotFoundException;
import ru.bogdanov.tindortest.likedservice.model.Liked;
import ru.bogdanov.tindortest.likedservice.service.LikedService;
import ru.bogdanov.tindortest.userservice.model.User;
import ru.bogdanov.tindortest.userservice.service.FileStorageService;
import ru.bogdanov.tindortest.userservice.service.UserService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/liked")
public class LikedController {
    private final LikedService likedService;
    private final UserService userService;
    private final FileStorageService fileStorageService;

    public LikedController(LikedService likedService, UserService userService, FileStorageService fileStorageService) {
        this.likedService = likedService;
        this.userService = userService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/all/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<LikedResponse>> getAllLikedByUserId(@PathVariable Long id) {
        List<LikedResponse> result = new ArrayList<>();

        for (Liked liked : likedService.findAllByUserId(id)) {
            User userLiked = userService.findById(liked.getUserLikedId());
            result.add(
                    LikedResponse.builder()
                            .id(liked.getId())
                            .userId(liked.getUserId())
                            .userLikedId(liked.getUserLikedId())
                            .status(liked.getStatus())
                            .name(userLiked.getName())
                            .photo(userLiked.getPhoto())
                            .build()
            );
        }

        return ResponseEntity.ok().body(result);
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

    @ControllerAdvice
    public static class LikedNotFoundController {

        @ResponseBody
        @ExceptionHandler(LikedNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public String likedNotFound(LikedNotFoundException e) {
            return e.getMessage();
        }
    }
}
