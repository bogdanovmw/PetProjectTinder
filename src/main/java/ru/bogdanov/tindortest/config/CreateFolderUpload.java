package ru.bogdanov.tindortest.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import ru.bogdanov.tindortest.service.FileStorageService;

import javax.annotation.Resource;

@Configuration
public class CreateFolderUpload implements CommandLineRunner {
    @Resource
    FileStorageService storageService;

    @Override
    public void run(String... args) throws Exception {
        storageService.init();
    }
}
