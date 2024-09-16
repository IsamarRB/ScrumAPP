package com.scrumapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scrumapp.model.ERole;
import com.scrumapp.model.User;
import com.scrumapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    private User user1;
    private User user2;
    private User user3;
    private List<User> userList;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        user1 = new User.Builder()
                .id(1)
                .username("juan")
                .email("juan@gmail.com")
                .password("123")
                .role(ERole.USER)
                .build();

        user2 = new User.Builder()
                .id(2)
                .username("juanita")
                .email("juanita@gmail.com")
                .password("1234")
                .role(ERole.ADMIN)
                .build();

        user3 = new User.Builder()
                .id(3)
                .username("paquito")
                .email("paquito@gmail.com")
                .password("12345")
                .role(ERole.MANAGER)
                .build();

        userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

    }

    @Test
    void createUserTest() throws Exception {
        when(userService.createUser(any(User.class))).thenReturn(user1);

        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(user1);

        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("juan"))
                .andExpect(jsonPath("$.role").value("USER"));
    }

    @Test
    void getAllUsersTest() {

    }

    @Test
    void getDonationIdTest() {
    }

    @Test
    void updateUserTest() {
    }

    @Test
    void deleteDonationByIdTest() {
    }
}