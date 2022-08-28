package ru.otus.spring.library.dao;

import ru.otus.spring.library.domain.Author;

import java.util.List;

public interface AuthorDao {
    long count();
    Author insert(Author author);
    void update(Author author);
    void delete(Author author);
    Author getById(long id);
    List <Author> getAll();
}
