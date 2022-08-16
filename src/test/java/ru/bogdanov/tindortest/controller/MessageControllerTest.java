package ru.bogdanov.tindortest.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.bogdanov.tindortest.model.Message;
import ru.bogdanov.tindortest.service.impl.MessageServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = MessageController.class)
class MessageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageServiceImpl messageService;

    private Message message = Message.builder()
                .id(1)
                .sender(1)
                .recipient(2)
                .addedAt("16.08.2022 18:00")
                .message("Hi, Anna!")
            .build();
}