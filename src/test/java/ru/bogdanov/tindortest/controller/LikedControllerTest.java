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
import ru.bogdanov.tindortest.controller.likedController.LikedController;
import ru.bogdanov.tindortest.model.Liked;
import ru.bogdanov.tindortest.service.impl.LikedServiceImpl;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = LikedController.class)
class LikedControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    private LikedServiceImpl likedService;

    private Liked liked = Liked.builder().id(1).userId(1).userLikedId(2).status(true).build();

    @Test
    void save() throws Exception {
        given(likedService.save(liked)).willReturn(liked);

        MockHttpServletRequestBuilder content = post("/api/liked")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(liked));

        mockMvc.perform(content)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId", is(liked.getUserId())))
                .andExpect(jsonPath("$.userLikedId", is(liked.getUserLikedId())));
    }

    @Test
    void deleteTest() throws Exception {
        doNothing().when(likedService).delete(liked.getId());

        MockHttpServletRequestBuilder content = delete("/api/liked/" + liked.getId())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(content)
                .andExpect(status().isNoContent());
    }

    @Test
    void findByIdTest() throws Exception {
        given(likedService.findById(liked.getId())).willReturn(liked);

        MockHttpServletRequestBuilder content = get("/api/liked/" + liked.getId())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(content)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(liked.getUserId())))
                .andExpect(jsonPath("$.userLikedId", is(liked.getUserLikedId())));
    }
}