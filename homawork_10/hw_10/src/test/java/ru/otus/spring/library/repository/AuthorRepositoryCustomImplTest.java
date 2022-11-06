package ru.otus.spring.library.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.library.domain.Author;
import ru.otus.spring.library.exceptions.HasDependentObjectsException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("JPA repository for authors")
@DataJpaTest
class AuthorRepositoryCustomImplTest {

    private static final long EXPECTED_AUTHORS_COUNT = 4;
    private static final long EXISTED_AUTHOR_ID = 2;
    private static final long EXISTED_AUTHOR_WITH_DEPENDENCY_ID = 1;
    @Autowired
    private AuthorRepositoryCustomImpl authorRepositoryCustom;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    void checkAuthorDeleted() throws HasDependentObjectsException {
        authorRepositoryCustom.deleteByIdWithDependencyException(EXISTED_AUTHOR_ID);
        long authorCount = authorRepository.count();
        assertEquals(authorCount, EXPECTED_AUTHORS_COUNT - 1);
        assertThat(testEntityManager.find(Author.class, EXISTED_AUTHOR_ID)).isNull();

    }

    @Test
    void checkAuthorWithBooksThrowExceptionOnDelete() {
        Author author = authorRepository.findById(EXISTED_AUTHOR_WITH_DEPENDENCY_ID).get();
        assertThrows(HasDependentObjectsException.class, () -> {
            authorRepositoryCustom.deleteByIdWithDependencyException(EXISTED_AUTHOR_WITH_DEPENDENCY_ID);
        }, "Author with dependency deleted and no exception thrown");
        long authorCount = authorRepository.findAll().size();
        assertEquals(authorCount, EXPECTED_AUTHORS_COUNT);
        Author newAuthorState = authorRepository.findById(EXISTED_AUTHOR_WITH_DEPENDENCY_ID).get();
        assertThat(author).usingRecursiveComparison().isEqualTo(newAuthorState);


    }

}