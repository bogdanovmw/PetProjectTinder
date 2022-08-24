package ru.bogdanov.tindortest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import ru.bogdanov.tindortest.chatservice.controller.MessageController;
import ru.bogdanov.tindortest.chatservice.model.Message;
import ru.bogdanov.tindortest.chatservice.service.impl.MessageServiceImpl;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = MessageController.class)
class MessageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    private MessageServiceImpl messageService;

    private Message message = Message.builder()
                .id(1L)
                .senderId("1")
                .recipientId("2")
                .addedAt("16.08.2022 18:00")
                .content("Hi, Anna!")
            .build();

    @Test
    void saveTest() throws Exception {
        given(messageService.save(message)).willReturn(message);

        MockHttpServletRequestBuilder content = post("/api/message")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(message));

        mockMvc.perform(content)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.sender", is(message.getSenderId())))
                .andExpect(jsonPath("$.recipient", is(message.getRecipientId()))).andReturn();

    }

    @Test
    void deleteTest() throws Exception {
        doNothing().when(messageService).delete(message.getId());

        MockHttpServletRequestBuilder content = delete("/api/message/" + message.getId())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(content)
                .andExpect(status().isNoContent());
    }

    @Test
    void findByIdTest() throws Exception {
        given(messageService.findById(message.getId())).willReturn(message);

        MockHttpServletRequestBuilder content = get("/api/message/" + message.getId())
                        .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(content)
                .andExpect(status().isOk())
                .andExpect(jsonPath("sender", is(message.getSenderId())))
                .andExpect(jsonPath("recipient", is(message.getRecipientId())));
    }
}