package ru.otus.spring.library.service;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.library.domain.Author;
import ru.otus.spring.library.repository.AuthorRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AuthorServiceImplTest {

    private static final String NEW_AUTHOR_NAME = "Иванов";
    private static final String EXISTED_AUTHOR_ID = "633362df00437c488c781798";
    private static final String EXISTED_AUTHOR_NAME = "Лермонтов";
    @Autowired
    private AuthorService authorService;
    @MockBean
    private AuthorRepository authorRepository;
    @Captor
    private ArgumentCaptor<Author> authorArgumentCaptor;

    @Test
    void testAddAuthor() {
        Author author = authorService.addAuthor(NEW_AUTHOR_NAME);
        Mockito.verify(authorRepository).save(author);
        assertEquals(author.getName(), NEW_AUTHOR_NAME);
    }

    @Test
    void testUpdateAuthor() {
        Mockito.when(authorRepository.findById(new ObjectId(EXISTED_AUTHOR_ID))).thenReturn(Optional.of(new Author(new ObjectId(EXISTED_AUTHOR_ID), EXISTED_AUTHOR_NAME)));
        authorService.updateAuthor(EXISTED_AUTHOR_ID, NEW_AUTHOR_NAME);
        Mockito.verify(authorRepository).save(authorArgumentCaptor.capture());
        Author capturedAuthor = authorArgumentCaptor.getValue();
        assertEquals(capturedAuthor.getId().toString(), EXISTED_AUTHOR_ID);
        assertEquals(capturedAuthor.getName(), NEW_AUTHOR_NAME);
    }

    @Test
    void testDeleteAuthor() {
        Mockito.when(authorRepository.findById(new ObjectId(EXISTED_AUTHOR_ID))).thenReturn(Optional.of(new Author(new ObjectId(EXISTED_AUTHOR_ID), EXISTED_AUTHOR_NAME)));
        authorService.deleteAuthor(EXISTED_AUTHOR_ID);
        Mockito.verify(authorRepository).deleteById(new ObjectId(EXISTED_AUTHOR_ID));

    }
}
