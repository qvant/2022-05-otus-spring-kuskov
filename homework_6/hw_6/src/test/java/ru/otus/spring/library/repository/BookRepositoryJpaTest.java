package ru.otus.spring.library.repository;//package ru.otus.spring.library.dao;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.library.domain.Author;
import ru.otus.spring.library.domain.Book;
import ru.otus.spring.library.domain.Genre;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("JPA repository for books")
@DataJpaTest
@Import(BookRepositoryJpa.class)
class BookRepositoryJpaTest {

    private static final long EXPECTED_BOOKS_COUNT = 5;
    private static final String EXISTED_BOOK_NAME = "Руслан и Людмила";
    private static final long EXISTED_BOOK_GENRE_ID = 2;
    private static final long EXISTED_BOOK_AUTHOR_ID = 1;
    private static final String EXISTED_BOOK_ISBN = "978-5-9268-2735-1";
    private static final long EXISTED_BOOK_ID = 1;
    private static final String NEW_BOOK_TITLE = "Игра форов";
    private static final String NEW_BOOK_ISBN = "553544545645";
    private static final long NEW_BOOK_AUTHOR_ID = 2;
    private static final String NEW_BOOK_AUTHOR_NAME = "Лермонтов";
    private static final long NEW_BOOK_GENRE_ID = 4;
    private static final String NEW_BOOK_GENRE_NAME = "Фантастика";
    @Autowired
    private BookRepositoryJpa bookRepositoryJpa;
    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void checkBooksCountIsCorrect() {
        long realBooksCount = bookRepositoryJpa.findAll().size();
        assertEquals(realBooksCount, EXPECTED_BOOKS_COUNT);
    }

    @Test
    void checkBookCreated() {
        Genre genre = new Genre(NEW_BOOK_GENRE_ID, NEW_BOOK_GENRE_NAME);
        Author author = new Author(NEW_BOOK_AUTHOR_ID, NEW_BOOK_AUTHOR_NAME);
        Book book = new Book(NEW_BOOK_TITLE, author, genre, NEW_BOOK_ISBN);
        bookRepositoryJpa.save(book);
        Book savedBook = testEntityManager.find(Book.class, book.getId());
        assertThat(savedBook).usingRecursiveComparison().isEqualTo(book);
    }

    @Test
    void checkBookCreatedOldStyle() {
        Genre genre = new Genre(NEW_BOOK_GENRE_ID, "");
        Author author = new Author(NEW_BOOK_AUTHOR_ID, "");
        Book book = new Book(NEW_BOOK_TITLE, author, genre, NEW_BOOK_ISBN);
        bookRepositoryJpa.save(book);
        Book savedBook = testEntityManager.find(Book.class, book.getId());
        assertEquals(savedBook.getTitle(), NEW_BOOK_TITLE);
        assertEquals(savedBook.getIsbn(), NEW_BOOK_ISBN);
        assertEquals(savedBook.getAuthor().getId(), NEW_BOOK_AUTHOR_ID);
        assertEquals(savedBook.getGenre().getId(), NEW_BOOK_GENRE_ID);
    }

    @Test
    void checkMassBookCreated() {
        Genre genre = new Genre(NEW_BOOK_GENRE_ID, NEW_BOOK_GENRE_NAME);
        Author author = new Author(NEW_BOOK_AUTHOR_ID, NEW_BOOK_AUTHOR_NAME);
        for (int i = 0; i < 1000; i++) {
            Book book = new Book(NEW_BOOK_TITLE, author, genre, NEW_BOOK_ISBN);
            bookRepositoryJpa.save(book);
        }
    }

    @Test
    void checkBookFindedById() {
        Book book = bookRepositoryJpa.findById(EXISTED_BOOK_ID).get();
        assertEquals(book.getId(), EXISTED_BOOK_ID);
        assertEquals(book.getTitle(), EXISTED_BOOK_NAME);
        assertEquals(book.getIsbn(), EXISTED_BOOK_ISBN);
        assertEquals(book.getAuthor().getId(), EXISTED_BOOK_AUTHOR_ID);
        assertEquals(book.getGenre().getId(), EXISTED_BOOK_GENRE_ID);
    }

    @Test
    void checkBookUpdated() {
        Book book = testEntityManager.find(Book.class, EXISTED_BOOK_ID);
        String newTitle = "20 000 лье под водой";
        String newIsbn = "555666";
        Genre genre = new Genre(NEW_BOOK_GENRE_ID, NEW_BOOK_GENRE_NAME);
        Author author = new Author(NEW_BOOK_AUTHOR_ID, NEW_BOOK_AUTHOR_NAME);
        Book modifiedBook = new Book(book.getId(), newTitle, author, genre, newIsbn);
        bookRepositoryJpa.save(modifiedBook);
        Book realModifiedBook = testEntityManager.find(Book.class, EXISTED_BOOK_ID);
        assertThat(modifiedBook).usingRecursiveComparison().isEqualTo(realModifiedBook);
    }

    @Test
    void checkBookDeleted() {
        bookRepositoryJpa.deleteById(EXISTED_BOOK_ID);
        long genreCount = bookRepositoryJpa.findAll().size();
        assertEquals(genreCount, EXPECTED_BOOKS_COUNT - 1);
        assertNull(testEntityManager.find(Book.class, EXISTED_BOOK_ID), "Book wasn't deleted properly");

    }

    @Test
    void checkGetAll() {
        List<Book> books = bookRepositoryJpa.findAll();
        Book existedBook = testEntityManager.find(Book.class, EXISTED_BOOK_ID);
        assertEquals(books.size(), EXPECTED_BOOKS_COUNT);
        assertTrue(books.stream().anyMatch(genre -> existedBook.getTitle().equals(genre.getTitle())));
        assertTrue(books.stream().anyMatch(genre -> existedBook.getId() == (genre.getId())));

    }

}

