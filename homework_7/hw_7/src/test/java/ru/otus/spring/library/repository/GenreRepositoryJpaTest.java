package ru.otus.spring.library.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.spring.library.domain.Genre;
import ru.otus.spring.library.exceptions.HasDependentObjectsException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("JPA repository for genres")
@DataJpaTest
class GenreRepositoryJpaTest {

    private static final long EXPECTED_GENRES_COUNT = 5;
    private static final long EXISTED_GENRE_ID = 5;
    private static final long EXISTED_GENRE_WITH_BOOKS_ID = 1;
    @Autowired
    private GenreRepositoryCustomImpl genreRepositoryCustom;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    void checkGenreDeleted() throws HasDependentObjectsException {
        genreRepositoryCustom.deleteByIdWithDependencyException(EXISTED_GENRE_ID);
        long genreCount = genreRepository.count();
        assertEquals(genreCount, EXPECTED_GENRES_COUNT - 1);
        assertNull(testEntityManager.find(Genre.class, EXISTED_GENRE_ID), "Genre wasn't deleted properly");
    }

    @Test
    void checkGenreWithBooksThrowExceptionOnDelete() {
        Genre genre = testEntityManager.find(Genre.class, EXISTED_GENRE_WITH_BOOKS_ID);
        assertThrows(HasDependentObjectsException.class, () -> {
            genreRepositoryCustom.deleteByIdWithDependencyException(EXISTED_GENRE_WITH_BOOKS_ID);
        }, "Genre with dependency deleted and no exception thrown");
        long genreCount = genreRepository.count();
        assertEquals(genreCount, EXPECTED_GENRES_COUNT);
        Genre newGenreState = testEntityManager.find(Genre.class, EXISTED_GENRE_WITH_BOOKS_ID);
        assertThat(genre).usingRecursiveComparison().isEqualTo(newGenreState);

    }

}
