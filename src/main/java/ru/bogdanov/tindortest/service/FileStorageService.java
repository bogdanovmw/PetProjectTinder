package ru.bogdanov.tindortest.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    void init();
    void save(MultipartFile file, String filename);
    void delete(String filename, Long id);
    Resource load(String filename, Long id);
}
