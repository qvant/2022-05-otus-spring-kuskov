package ru.otus.spring.library.repository;

import ru.otus.spring.library.domain.Book;

import java.util.List;
import java.util.Optional;


public interface BookRepository {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    Book save(Book book);

    void deleteById(Long id);
}
