package ru.otus.spring.library.dao;

import ru.otus.spring.library.domain.Book;

import java.util.List;

public interface BookDao {
    long count();

    void insert(Book book);

    void update(Book book);

    void delete(Book book);

    Book getById(long id);

    List<Book> getAll();
}
