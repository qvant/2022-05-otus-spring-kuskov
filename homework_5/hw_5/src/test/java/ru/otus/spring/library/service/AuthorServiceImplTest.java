package ru.otus.spring.library.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.library.dao.AuthorDaoJdbc;
import ru.otus.spring.library.domain.Author;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import({AuthorDaoJdbc.class, AuthorServiceImpl.class, IOServiceConsole.class})
class AuthorServiceImplTest {
    @Autowired
    private AuthorServiceImpl authorService;
    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    private static final String NEW_AUTHOR_NAME = "Иванов";
    private static final long EXISTED_AUTHOR_ID = 2;


    @Test
    void testAddAuthor() {
        Author author = authorService.addAuthor(NEW_AUTHOR_NAME);
        Author savedAuthor = authorDaoJdbc.getById(author.getId());
        assertEquals(NEW_AUTHOR_NAME, savedAuthor.getName());
        assertEquals(author.getId(), savedAuthor.getId());
    }

    @Test
    void testUpdateAuthor() {
        Author author = authorService.addAuthor(NEW_AUTHOR_NAME);
        Author savedAuthor = authorDaoJdbc.getById(author.getId());
        assertEquals(NEW_AUTHOR_NAME, savedAuthor.getName());
        assertEquals(author.getId(), savedAuthor.getId());
    }

    @Test
    void testDeleteAuthor() {
        long authorsCountBefore = authorDaoJdbc.count();
        authorService.deleteAuthor(EXISTED_AUTHOR_ID);
        long authorsCountAfter = authorDaoJdbc.count();
        assertEquals(authorsCountBefore, authorsCountAfter + 1);
    }

}