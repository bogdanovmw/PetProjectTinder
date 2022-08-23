package ru.bogdanov.tindortest.controller.userController;

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
import ru.bogdanov.tindortest.model.User;
import ru.bogdanov.tindortest.service.impl.UserServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    private UserServiceImpl userService;

    private User user = User.builder()
                .id(1)
                .name("Max")
                .email("max@mail.ru")
                .password("Qwe123321!")
                .photo("Test")
            .build();

    @Test
    void saveTest() throws Exception {
        given(userService.save(user)).willReturn(user);

        MockHttpServletRequestBuilder content = post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user));

        mockMvc.perform(content)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(user.getName())));
    }

    @Test
    void deleteTest() throws Exception {
        doNothing().when(userService).delete(user.getId());

        MockHttpServletRequestBuilder content = delete("/api/user/" + user.getId())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(content)
                .andExpect(status().isNoContent());
    }

    @Test
    void findByIdTest() throws Exception {
        given(userService.findById(user.getId())).willReturn(user);

        MockHttpServletRequestBuilder content = get("/api/user/" + user.getId())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(content)
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(user.getName())));
    }

    @Test
    void findAll() throws Exception {
        List<User> users = Arrays.asList(user);
        given(userService.findAll()).willReturn(users);

        MockHttpServletRequestBuilder content = get("/api/user").contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(content)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(user.getName())));
    }
}