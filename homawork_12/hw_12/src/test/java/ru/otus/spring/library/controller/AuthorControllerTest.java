package ru.otus.spring.library.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.library.domain.User;
import ru.otus.spring.library.security.SecurityConfiguration;
import ru.otus.spring.library.service.AuthorService;
import ru.otus.spring.library.service.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(SpringExtension.class)
//@WebMvcTest(AuthorController.class)
@WebMvcTest({AuthorController.class, SecurityConfiguration.class, UserService.class})
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private UserService userService;

    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})

    @BeforeEach
    void setUp() {

    }

    @Test
    void listPage() throws Exception{
        Mockito.when(userService.loadUserByUsername("admin")).thenReturn(new User(-1L, "Admin", "25", false));
        mockMvc.perform(get("/authors")).andExpect(status().isOk());
    }
}