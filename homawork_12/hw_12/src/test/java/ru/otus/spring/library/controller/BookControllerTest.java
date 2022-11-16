package ru.otus.spring.library.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.library.domain.Author;
import ru.otus.spring.library.domain.Book;
import ru.otus.spring.library.domain.Genre;
import ru.otus.spring.library.domain.User;
import ru.otus.spring.library.repository.GenreRepository;
import ru.otus.spring.library.security.SecurityConfiguration;
import ru.otus.spring.library.service.AuthorService;
import ru.otus.spring.library.service.BookService;
import ru.otus.spring.library.service.GenreService;
import ru.otus.spring.library.service.UserService;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({BookController.class, SecurityConfiguration.class, UserService.class})
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private GenreService genreService;
    @MockBean
    private UserService userService;

    @Test
    void listPageUnauth() throws Exception {

        Mockito.when(userService.loadUserByUsername("admin")).thenReturn(new User(-1L, "Admin", "25", false));
        mockMvc.perform(get("/books")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void listPageAuth() throws Exception {
        Mockito.when(userService.loadUserByUsername("admin")).thenReturn(new User(-1L, "Admin", "25", false));
        mockMvc.perform(get("/books")).andExpect(status().isOk());
    }

    @Test
    void createPageUnauth() throws Exception {

        Mockito.when(userService.loadUserByUsername("admin")).thenReturn(new User(-1L, "Admin", "25", false));
        mockMvc.perform(get("/bookCreate")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void createPageAuth() throws Exception {
        Mockito.when(userService.loadUserByUsername("admin")).thenReturn(new User(-1L, "Admin", "25", false));
        mockMvc.perform(get("/bookCreate")).andExpect(status().isOk());
    }

    @Test
    void editPageUnauth() throws Exception {
        Mockito.when(bookService.findById(1)).thenReturn(Optional.of(new Book(1, "Paceholder", new Author(1, "A"), new Genre(1, "A"), "1")));
        Mockito.when(userService.loadUserByUsername("admin")).thenReturn(new User(-1L, "Admin", "25", false));
        mockMvc.perform(get("/bookEdit?id=1")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void editPageAuth() throws Exception {
        Mockito.when(bookService.findById(1)).thenReturn(Optional.of(new Book(1, "Paceholder", new Author(1, "A"), new Genre(1, "A"), "1")));
        Mockito.when(userService.loadUserByUsername("admin")).thenReturn(new User(-1L, "Admin", "25", false));
        mockMvc.perform(get("/bookEdit?id=1")).andExpect(status().isOk());
    }

    @Test
    void deletePageUnauth() throws Exception {
        Mockito.when(bookService.findById(1)).thenReturn(Optional.of(new Book(1, "Paceholder", new Author(1, "A"), new Genre(1, "A"), "1")));
        Mockito.when(userService.loadUserByUsername("admin")).thenReturn(new User(-1L, "Admin", "25", false));
        mockMvc.perform(get("/bookDelete?id=1")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void deletePageAuth() throws Exception {
        Mockito.when(bookService.findById(1)).thenReturn(Optional.of(new Book(1, "Paceholder", new Author(1, "A"), new Genre(1, "A"), "1")));
        Mockito.when(userService.loadUserByUsername("admin")).thenReturn(new User(-1L, "Admin", "25", false));
        mockMvc.perform(get("/bookDelete?id=1")).andExpect(status().isOk());
    }
}