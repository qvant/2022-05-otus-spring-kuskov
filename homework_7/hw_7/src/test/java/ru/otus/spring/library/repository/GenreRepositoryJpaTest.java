package ru.otus.spring.library.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.library.domain.Genre;
import ru.otus.spring.library.exceptions.HasDependentObjectsException;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("JPA repository for genres")
@DataJpaTest
@Import(GenreRepositoryJpa.class)
class GenreRepositoryJpaTest {

    private static final long EXPECTED_GENRES_COUNT = 5;
    private static final String EXISTED_GENRE_NAME = "Мемуары";
    private static final long EXISTED_GENRE_ID = 5;
    private static final long EXISTED_GENRE_WITH_BOOKS_ID = 1;
    private static final String NEW_GENRE_NAME = "Лит РПГ";
    @Autowired
    private GenreRepositoryJpa genreRepositoryJpa;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void checkGenresCountIsCorrect() {
        long realGenresCount = genreRepositoryJpa.findAll().size();
        assertEquals(realGenresCount, EXPECTED_GENRES_COUNT);
    }

    @Test
    void checkGenreCreated() {
        Genre genre = new Genre(NEW_GENRE_NAME);
        genreRepositoryJpa.save(genre);
        Genre savedGenre = testEntityManager.find(Genre.class, genre.getId());
        assertThat(savedGenre).usingRecursiveComparison().isEqualTo(genre);
    }

    @Test
    void checkGenreCreatedOldStyle() {
        Genre genre = new Genre(NEW_GENRE_NAME);
        genreRepositoryJpa.save(genre);
        Genre savedGenre = testEntityManager.find(Genre.class, genre.getId());
        assertEquals(savedGenre.getName(), NEW_GENRE_NAME);
    }

    @Test
    void checkGenreFoundById() {
        Genre genre = genreRepositoryJpa.findById(EXISTED_GENRE_ID).get();
        assertEquals(genre.getId(), EXISTED_GENRE_ID);
        assertEquals(genre.getName(), EXISTED_GENRE_NAME);
    }

    @Test
    void checkGenreUpdated() {
        Genre genre = testEntityManager.find(Genre.class, EXISTED_GENRE_ID);
        String newName = "Мусин-Пушкин";
        Genre modifiedGenre = new Genre(genre.getId(), newName);
        genreRepositoryJpa.save(modifiedGenre);
        Genre realModifiedGenre = testEntityManager.find(Genre.class, EXISTED_GENRE_ID);
        assertThat(modifiedGenre).usingRecursiveComparison().isEqualTo(realModifiedGenre);
    }

    @Test
    void checkGenreDeleted() throws HasDependentObjectsException {
        genreRepositoryJpa.deleteById(EXISTED_GENRE_ID);
        long genreCount = genreRepositoryJpa.findAll().size();
        assertEquals(genreCount, EXPECTED_GENRES_COUNT - 1);
        assertNull(testEntityManager.find(Genre.class, EXISTED_GENRE_ID), "Genre wasn't deleted properly");
    }

    @Test
    void checkGenreWithBooksThrowExceptionOnDelete() {
        Genre genre = testEntityManager.find(Genre.class, EXISTED_GENRE_WITH_BOOKS_ID);
        assertThrows(HasDependentObjectsException.class, () -> {
            genreRepositoryJpa.deleteById(EXISTED_GENRE_WITH_BOOKS_ID);
        }, "Genre with dependency deleted and no exception thrown");
        long genreCount = genreRepositoryJpa.findAll().size();
        assertEquals(genreCount, EXPECTED_GENRES_COUNT);
        Genre newGenreState = testEntityManager.find(Genre.class, EXISTED_GENRE_WITH_BOOKS_ID);
        assertThat(genre).usingRecursiveComparison().isEqualTo(newGenreState);

    }

    @Test
    void checkGetAll() {
        List<Genre> genres = genreRepositoryJpa.findAll();
        Genre existedGenre = testEntityManager.find(Genre.class, EXISTED_GENRE_ID);
        assertEquals(genres.size(), EXPECTED_GENRES_COUNT);
        assertTrue(genres.stream().anyMatch(genre -> existedGenre.getName().equals(genre.getName())));
        assertTrue(genres.stream().anyMatch(genre -> existedGenre.getId() == (genre.getId())));

    }

}
