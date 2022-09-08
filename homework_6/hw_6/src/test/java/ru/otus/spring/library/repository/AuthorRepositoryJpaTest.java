package ru.otus.spring.library.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.library.domain.Author;
import ru.otus.spring.library.exceptions.HasDependentObjectsException;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JPA repository for authors")
@DataJpaTest
@Import(AuthorRepositoryJpa.class)
class AuthorRepositoryJpaTest {

    private static final long EXPECTED_AUTHORS_COUNT = 4;
    private static final String EXISTED_AUTHOR_NAME = "Лермонтов";
    private static final long EXISTED_AUTHOR_ID = 2;
    private static final long EXISTED_AUTHOR_WITH_DEPENDENCY_ID = 1;
    private static final String NEW_AUTHOR_NAME = "Переслегин";
    @Autowired
    private AuthorRepositoryJpa authorRepositoryJpa;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void checkAuthorsCountIsCorrect() {
        long realAuthorsCount = authorRepositoryJpa.findAll().size();
        assertEquals(realAuthorsCount, EXPECTED_AUTHORS_COUNT);
    }

    @Test
    void checkAuthorCreated() {
        Author author = new Author(NEW_AUTHOR_NAME);
        authorRepositoryJpa.save(author);
        Author savedAuthor = testEntityManager.find(Author.class, author.getId());
        assertThat(savedAuthor).usingRecursiveComparison().isEqualTo(author);
    }

    @Test
    void checkAuthorCreatedOldStyle() {
        Author author = new Author(NEW_AUTHOR_NAME);
        authorRepositoryJpa.save(author);
        Author savedAuthor = testEntityManager.find(Author.class, author.getId());
        assertEquals(savedAuthor.getName(), NEW_AUTHOR_NAME);
    }

    @Test
    void checkAuthorFindedById() {
        Author author = authorRepositoryJpa.findById(EXISTED_AUTHOR_ID).get();
        assertEquals(author.getId(), EXISTED_AUTHOR_ID);
        assertEquals(author.getName(), EXISTED_AUTHOR_NAME);
    }

    @Test
    void checkAuthorUpdated() {
        Author author = testEntityManager.find(Author.class, EXISTED_AUTHOR_ID);
        String newName = "Мусин-Пушкин";
        Author modifiedAuthor = new Author(author.getId(), newName);
        authorRepositoryJpa.save(modifiedAuthor);
        Author realModifiedAuthor = testEntityManager.find(Author.class, EXISTED_AUTHOR_ID);
        assertThat(modifiedAuthor).usingRecursiveComparison().isEqualTo(realModifiedAuthor);
    }

    @Test
    void checkAuthorDeleted() throws HasDependentObjectsException {
        authorRepositoryJpa.deleteById(EXISTED_AUTHOR_ID);
        long authorCount = authorRepositoryJpa.findAll().size();
        assertEquals(authorCount, EXPECTED_AUTHORS_COUNT - 1);
        assertThat(testEntityManager.find(Author.class, EXISTED_AUTHOR_ID)).isNull();

    }

    @Test
    void checkAuthorWithBooksThrowExceptionOnDelete() {
        Author author = authorRepositoryJpa.findById(EXISTED_AUTHOR_WITH_DEPENDENCY_ID).get();
        assertThrows(HasDependentObjectsException.class, () -> {
            authorRepositoryJpa.deleteById(EXISTED_AUTHOR_WITH_DEPENDENCY_ID);
        }, "Author with dependency deleted and no exception thrown");
        long authorCount = authorRepositoryJpa.findAll().size();
        assertEquals(authorCount, EXPECTED_AUTHORS_COUNT);
        Author newAuthorState = authorRepositoryJpa.findById(EXISTED_AUTHOR_WITH_DEPENDENCY_ID).get();
        assertThat(author).usingRecursiveComparison().isEqualTo(newAuthorState);


    }

    @Test
    void checkGetAll() {
        List<Author> authors = authorRepositoryJpa.findAll();
        Author existedAuthor = authorRepositoryJpa.findById(EXISTED_AUTHOR_ID).get();
        assertEquals(authors.size(), EXPECTED_AUTHORS_COUNT);
        assertTrue(authors.stream().anyMatch(author -> existedAuthor.getName().equals(author.getName())));
        assertTrue(authors.stream().anyMatch(author -> existedAuthor.getId() == (author.getId())));

    }

}