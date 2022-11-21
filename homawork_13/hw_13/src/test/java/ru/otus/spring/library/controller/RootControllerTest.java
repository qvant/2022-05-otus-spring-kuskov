package ru.otus.spring.library.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.library.domain.User;
import ru.otus.spring.library.security.SecurityConfiguration;
import ru.otus.spring.library.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({RootController.class, SecurityConfiguration.class, UserService.class})
class RootControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Test
    void listPageUnauth() throws Exception {

        Mockito.when(userService.loadUserByUsername("admin")).thenReturn(new User(-1L, "Admin", "25", false));
        mockMvc.perform(get("/")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void listPageAuth() throws Exception {
        Mockito.when(userService.loadUserByUsername("admin")).thenReturn(new User(-1L, "Admin", "25", false));
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

}