package ru.bogdanov.tindortest.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;


@SpringBootTest
class FilesStorageServiceImplTest {
    @MockBean
    private FilesStorageServiceImpl storageService;

    @Mock
    private MultipartFile file;
    @Mock
    private Resource resource;

    @Test
    void initTest() {
        doNothing().when(storageService).init();
    }

    @Test
    void saveTest() {
        doThrow(new RuntimeException()).when(storageService).save(file, "test.jpg");
    }

    @Test
    void loadTest() throws MalformedURLException {
        given(storageService.load(anyString(), anyLong())).willReturn(resource);
    }
}