package ru.bogdanov.tindortest.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bogdanov.tindortest.userservice.dto.UsersResponse;
import ru.bogdanov.tindortest.likedservice.model.Liked;
import ru.bogdanov.tindortest.userservice.model.User;
import ru.bogdanov.tindortest.userservice.service.FileStorageService;
import ru.bogdanov.tindortest.likedservice.service.LikedService;
import ru.bogdanov.tindortest.userservice.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final UserService userService;
    private final FileStorageService fileStorageService;
    private final LikedService likedService;

    public UsersController(UserService userService, FileStorageService fileStorageService, LikedService likedService) {
        this.userService = userService;
        this.fileStorageService = fileStorageService;
        this.likedService = likedService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UsersResponse>> getAllByIdNot(@RequestParam Long id) {
        List<UsersResponse> result = new ArrayList<>();
        List<UsersResponse> resultLiked = new ArrayList<>();

        // Получаем список все пользователей кроме, авторизированного
        for (User user : userService.findAllByIdNot(id))
            result.add(userBuild(user.getId(), user.getName(), user.getPhoto()));

        // Сравниваем таблицу user и таблицу liked, если user.getId и liked.getUserLikedId совпадают,
        // значит что авторизированный пользователь уже лайкал этого user, и он нам не понадобиться,
        // находим всех user которых лайкал авторизированный пользователь
        for (UsersResponse user : result)
            for (Liked liked : likedService.findAllByUserId(id))
                if (user.getId() == liked.getUserLikedId())
                    resultLiked.add(userBuild(user.getId(), user.getName(), user.getPhoto()));

        // Оставляем только тех пользователей которые еще не пролайканы
        result.removeAll(resultLiked);

        return ResponseEntity.ok().body(result);
    }

    private UsersResponse userBuild(long id, String name, String photo) {
        return UsersResponse.builder()
                .id(id)
                .name(name)
                .photo(photo)
                .build();
    }
}
