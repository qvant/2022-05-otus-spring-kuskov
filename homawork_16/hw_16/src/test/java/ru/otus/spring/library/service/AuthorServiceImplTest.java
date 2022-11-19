package ru.otus.spring.library.service;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.library.domain.Author;
import ru.otus.spring.library.exceptions.HasDependentObjectsException;
import ru.otus.spring.library.repository.AuthorRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AuthorServiceImplTest {

    private static final String NEW_AUTHOR_NAME = "Иванов";
    private static final long EXISTED_AUTHOR_ID = 2;
    private static final String EXISTED_AUTHOR_NAME = "Лермонтов";
    @Autowired
    private AuthorService authorService;
    @MockBean
    private AuthorRepository authorRepository;
    @Captor
    private ArgumentCaptor<Long> authorIdArgumentCaptor;

    @Test
    void testAddAuthor() {
        Author author = authorService.addAuthor(NEW_AUTHOR_NAME);
        Mockito.verify(authorRepository).save(author);
        assertEquals(author.getName(), NEW_AUTHOR_NAME);
    }

    @Test
    void testUpdateAuthor() {
        Author savedAuthor = authorService.updateAuthor(EXISTED_AUTHOR_ID, EXISTED_AUTHOR_NAME);
        Mockito.verify(authorRepository).save(savedAuthor);
        assertEquals(EXISTED_AUTHOR_NAME, savedAuthor.getName());
        assertEquals(EXISTED_AUTHOR_ID, savedAuthor.getId());
    }

    @Test
    void testDeleteAuthor() throws HasDependentObjectsException {
        authorService.deleteAuthor(EXISTED_AUTHOR_ID);
        Mockito.verify(authorRepository).deleteByIdWithDependencyException(authorIdArgumentCaptor.capture());
        Long argumentValue = authorIdArgumentCaptor.getValue();
        assertEquals(argumentValue, EXISTED_AUTHOR_ID);
    }
}
