package ru.bogdanov.tindortest.userservice.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.bogdanov.tindortest.userservice.model.User;
import ru.bogdanov.tindortest.userservice.service.FileStorageService;
import ru.bogdanov.tindortest.userservice.service.UserService;

import javax.activation.FileTypeMap;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/user/file")
public class UserPhotoController {
    private final UserService userService;
    private final FileStorageService fileStorageService;

    public UserPhotoController(UserService userService, FileStorageService fileStorageService) {
        this.userService = userService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resource> getFile(@PathVariable Long id) throws IOException {
        User user = userService.findById(id);

        Resource file = fileStorageService.load(user.getPhoto(), id);

        return ResponseEntity
                .ok()
//                .header("Content-Disposition", "attachment; filename=" + photo) - скачивает фото
                .contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(file.getFile()))) // показывает фото в браузере
                .body(file);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> savePhoto(@RequestParam("id") Long id, @RequestParam("file") MultipartFile file) {
        String username = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String message = "";

        try {
            userService.savePhoto(id, username);
            fileStorageService.save(file, username);
            message = "Uploaded the file successfully: " + username;

            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Could not upload the file: " + username + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }
}
