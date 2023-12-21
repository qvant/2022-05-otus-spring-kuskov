package ru.otus.spring.library.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.library.domain.*;
import ru.otus.spring.library.security.SecurityConfiguration;
import ru.otus.spring.library.service.CommentService;
import ru.otus.spring.library.service.UserService;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({CommentController.class, SecurityConfiguration.class, UserService.class})
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CommentService commentService;
    @MockBean
    private UserService userService;

    @Test
    void listPageUnauth() throws Exception {

        Mockito.when(userService.loadUserByUsername("admin")).thenReturn(new User(-1L, "Admin", "25", false));
        mockMvc.perform(get("/comments?book_id=1")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void listPageAuth() throws Exception {
        Mockito.when(userService.loadUserByUsername("admin")).thenReturn(new User(-1L, "Admin", "25", false));
        mockMvc.perform(get("/comments?book_id=1")).andExpect(status().isOk());
    }

    @Test
    void createPageUnauth() throws Exception {

        Mockito.when(userService.loadUserByUsername("admin")).thenReturn(new User(-1L, "Admin", "25", false));
        mockMvc.perform(get("/commentCreate?book_id=1")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void createPageAuth() throws Exception {
        Mockito.when(userService.loadUserByUsername("admin")).thenReturn(new User(-1L, "Admin", "25", false));
        mockMvc.perform(get("/commentCreate?book_id=1")).andExpect(status().isOk());
    }

    @Test
    void editPageUnauth() throws Exception {
        Mockito.when(commentService.findById(1)).thenReturn(Optional.of(new Comment(new Book(1, "Paceholder", new Author(1, "A"), new Genre(1, "A"), "1"), "Paceholder")));
        Mockito.when(userService.loadUserByUsername("admin")).thenReturn(new User(-1L, "Admin", "25", false));
        mockMvc.perform(get("/commentEdit?id=1")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void editPageAuth() throws Exception {
        Mockito.when(commentService.findById(1)).thenReturn(Optional.of(new Comment(new Book(1, "Paceholder", new Author(1, "A"), new Genre(1, "A"), "1"), "Paceholder")));
        Mockito.when(userService.loadUserByUsername("admin")).thenReturn(new User(-1L, "Admin", "25", false));
        mockMvc.perform(get("/commentEdit?id=1")).andExpect(status().isOk());
    }

    @Test
    void deletePageUnauth() throws Exception {
        Mockito.when(commentService.findById(1)).thenReturn(Optional.of(new Comment(new Book(1, "Paceholder", new Author(1, "A"), new Genre(1, "A"), "1"), "Paceholder")));
        Mockito.when(userService.loadUserByUsername("admin")).thenReturn(new User(-1L, "Admin", "25", false));
        mockMvc.perform(get("/commentDelete?id=1")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void deletePageAuth() throws Exception {
        Mockito.when(commentService.findById(1)).thenReturn(Optional.of(new Comment(new Book(1, "Paceholder", new Author(1, "A"), new Genre(1, "A"), "1"), "Paceholder")));
        Mockito.when(userService.loadUserByUsername("admin")).thenReturn(new User(-1L, "Admin", "25", false));
        mockMvc.perform(get("/commentDelete?id=1")).andExpect(status().isOk());
    }
}