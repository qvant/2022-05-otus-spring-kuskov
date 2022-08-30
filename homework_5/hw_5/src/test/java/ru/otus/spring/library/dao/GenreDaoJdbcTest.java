package ru.otus.spring.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.library.domain.Genre;
import ru.otus.spring.library.exceptions.HasDependentObjectsException;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Dao for genres")
@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {

    private static final long EXPECTED_GENRES_COUNT = 5;
    private static final String EXISTED_GENRE_NAME = "Мемуары";
    private static final long EXISTED_GENRE_ID = 5;
    private static final long EXISTED_GENRE_WITH_BOOKS_ID = 1;
    private static final String NEW_GENRE_NAME = "Лит РПГ";
    @Autowired
    private GenreDaoJdbc GenreDaoJdbc;

    @Test
    void checkGenresCountIsCorrect() {
        long realGenresCount = GenreDaoJdbc.count();
        assertEquals(realGenresCount, EXPECTED_GENRES_COUNT);
    }

    @Test
    void checkGenreCreated() {
        Genre genre = new Genre(NEW_GENRE_NAME);
        GenreDaoJdbc.insert(genre);
        Genre savedGenre = GenreDaoJdbc.getById(genre.getId());
        assertThat(savedGenre).usingRecursiveComparison().isEqualTo(genre);
    }

    @Test
    void checkGenreCreatedOldStyle() {
        Genre genre = new Genre(NEW_GENRE_NAME);
        GenreDaoJdbc.insert(genre);
        Genre savedGenre = GenreDaoJdbc.getById(genre.getId());
        assertEquals(savedGenre.getName(), NEW_GENRE_NAME);
    }

    @Test
    void checkGenreFindedById() {
        Genre genre = GenreDaoJdbc.getById(EXISTED_GENRE_ID);
        assertEquals(genre.getId(), EXISTED_GENRE_ID);
        assertEquals(genre.getName(), EXISTED_GENRE_NAME);
    }

    @Test
    void checkGenreUpdated() {
        Genre genre = GenreDaoJdbc.getById(EXISTED_GENRE_ID);
        String newName = "Мусин-Пушкин";
        Genre modifiedGenre = new Genre(genre.getId(), newName);
        GenreDaoJdbc.update(modifiedGenre);
        Genre realModifiedGenre = GenreDaoJdbc.getById(EXISTED_GENRE_ID);
        assertThat(modifiedGenre).usingRecursiveComparison().isEqualTo(realModifiedGenre);
    }

    @Test
    void checkGenreDeleted() throws HasDependentObjectsException {
        Genre genre = GenreDaoJdbc.getById(EXISTED_GENRE_ID);
        GenreDaoJdbc.delete(genre);
        long genreCount = GenreDaoJdbc.count();
        assertEquals(genreCount, EXPECTED_GENRES_COUNT - 1);
        assertThrows(org.springframework.dao.EmptyResultDataAccessException.class, () -> {
            GenreDaoJdbc.getById(EXISTED_GENRE_ID);
        }, "Genre wasn't deleted properly");

    }

    @Test
    void checkGenreWithBooksThrowExceptionOnDelete() {
        Genre genre = GenreDaoJdbc.getById(EXISTED_GENRE_WITH_BOOKS_ID);
        assertThrows(HasDependentObjectsException.class, () -> {
            GenreDaoJdbc.delete(genre);
        }, "Genre with dependency deleted and no exception thrown");
        long genreCount = GenreDaoJdbc.count();
        assertEquals(genreCount, EXPECTED_GENRES_COUNT);
        Genre newGenreState = GenreDaoJdbc.getById(EXISTED_GENRE_WITH_BOOKS_ID);
        assertThat(genre).usingRecursiveComparison().isEqualTo(newGenreState);

    }

    @Test
    void checkGetAll() {
        List<Genre> genres = GenreDaoJdbc.getAll();
        Genre existedGenre = GenreDaoJdbc.getById(EXISTED_GENRE_ID);
        assertEquals(genres.size(), EXPECTED_GENRES_COUNT);
        assertTrue(genres.stream().anyMatch(genre -> existedGenre.getName().equals(genre.getName())));
        assertTrue(genres.stream().anyMatch(genre -> existedGenre.getId() == (genre.getId())));

    }

}
