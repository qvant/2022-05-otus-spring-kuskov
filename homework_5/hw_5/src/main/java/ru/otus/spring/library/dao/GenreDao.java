package ru.otus.spring.library.dao;

import ru.otus.spring.library.domain.Genre;
import ru.otus.spring.library.exceptions.HasDependentObjectsException;

import java.util.List;

public interface GenreDao {
    long count();

    void insert(Genre genre);

    void update(Genre genre);

    void delete(Genre genre) throws HasDependentObjectsException;

    Genre getById(long id);

    List<Genre> getAll();
}
