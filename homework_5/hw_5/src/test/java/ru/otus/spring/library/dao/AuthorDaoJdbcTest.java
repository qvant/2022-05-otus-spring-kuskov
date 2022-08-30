package ru.otus.spring.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.library.domain.Author;
import ru.otus.spring.library.exceptions.HasDependentObjectsException;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Dao for authors")
@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    private static final long EXPECTED_AUTHORS_COUNT = 4;
    private static final String EXISTED_AUTHOR_NAME = "Лермонтов";
    private static final long EXISTED_AUTHOR_ID = 2;
    private static final long EXISTED_AUTHOR_WITH_DEPENDENCY_ID = 1;
    private static final String NEW_AUTHOR_NAME = "Переслегин";
    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    @Test
    void checkAuthorsCountIsCorrect() {
        long realAuthorsCount = authorDaoJdbc.count();
        assertEquals(realAuthorsCount, EXPECTED_AUTHORS_COUNT);
    }

    @Test
    void checkAuthorCreated() {
        Author author = new Author(NEW_AUTHOR_NAME);
        authorDaoJdbc.insert(author);
        Author savedAuthor = authorDaoJdbc.getById(author.getId());
        assertThat(savedAuthor).usingRecursiveComparison().isEqualTo(author);
    }

    @Test
    void checkAuthorCreatedOldStyle() {
        Author author = new Author(NEW_AUTHOR_NAME);
        authorDaoJdbc.insert(author);
        Author savedAuthor = authorDaoJdbc.getById(author.getId());
        assertEquals(savedAuthor.getName(), NEW_AUTHOR_NAME);
    }

    @Test
    void checkAuthorFindedById() {
        Author author = authorDaoJdbc.getById(EXISTED_AUTHOR_ID);
        assertEquals(author.getId(), EXISTED_AUTHOR_ID);
        assertEquals(author.getName(), EXISTED_AUTHOR_NAME);
    }

    @Test
    void checkAuthorUpdated() {
        Author author = authorDaoJdbc.getById(EXISTED_AUTHOR_ID);
        String newName = "Мусин-Пушкин";
        Author modifiedAuthor = new Author(author.getId(), newName);
        authorDaoJdbc.update(modifiedAuthor);
        Author realModifiedAuthor = authorDaoJdbc.getById(EXISTED_AUTHOR_ID);
        assertThat(modifiedAuthor).usingRecursiveComparison().isEqualTo(realModifiedAuthor);
    }

    @Test
    void checkAuthorDeleted() throws HasDependentObjectsException {
        Author author = authorDaoJdbc.getById(EXISTED_AUTHOR_ID);
        authorDaoJdbc.delete(author);
        long authorCount = authorDaoJdbc.count();
        assertEquals(authorCount, EXPECTED_AUTHORS_COUNT - 1);
        assertThrows(org.springframework.dao.EmptyResultDataAccessException.class, ()->{authorDaoJdbc.getById(EXISTED_AUTHOR_ID);}, "Author wasn't deleted properly");

    }

    @Test
    void checkAuthorWithBooksThrowExceptionOnDelete() {
        Author author = authorDaoJdbc.getById(EXISTED_AUTHOR_WITH_DEPENDENCY_ID);
        assertThrows(HasDependentObjectsException.class, () ->{authorDaoJdbc.delete(author);}, "Author with dependency deleted and no exception thrown");
        long authorCount = authorDaoJdbc.count();
        assertEquals(authorCount, EXPECTED_AUTHORS_COUNT);
        Author newAuthorState = authorDaoJdbc.getById(EXISTED_AUTHOR_WITH_DEPENDENCY_ID);
        assertThat(author).usingRecursiveComparison().isEqualTo(newAuthorState);


    }

    @Test
    void checkGetAll() {
        List<Author> authors = authorDaoJdbc.getAll();
        Author existedAuthor = authorDaoJdbc.getById(EXISTED_AUTHOR_ID);
        assertEquals(authors.size(), EXPECTED_AUTHORS_COUNT);
        assertTrue(authors.stream().anyMatch(author -> existedAuthor.getName().equals(author.getName())));
        assertTrue(authors.stream().anyMatch(author -> existedAuthor.getId() == (author.getId())));

    }

}