package ru.otus.spring.library.dao;

import ru.otus.spring.library.domain.Author;
import ru.otus.spring.library.exceptions.HasDependentObjectsException;

import java.util.List;

public interface AuthorDao {
    long count();

    void insert(Author author);

    void update(Author author);

    void delete(Author author) throws HasDependentObjectsException;

    Author getById(long id);

    List<Author> getAll();
}
