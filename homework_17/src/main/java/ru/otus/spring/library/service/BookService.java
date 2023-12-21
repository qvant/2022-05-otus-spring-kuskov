package ru.otus.spring.library.service;

import ru.otus.spring.library.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    void addBook(String title, Long authorId, Long genreId, String isbn);

    void updateBook(long id, String title, long authorId, long genreId, String isbn);

    void deleteBook(long id);

    List<Book> findAll();

    Optional<Book> findById(long id);

}
