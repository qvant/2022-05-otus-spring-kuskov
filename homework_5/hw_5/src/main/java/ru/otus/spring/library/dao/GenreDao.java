package ru.otus.spring.library.dao;

import ru.otus.spring.library.domain.Author;
import ru.otus.spring.library.domain.Genre;

import java.util.List;

public interface GenreDao {
    long count();
    void insert(Genre genre);
    void update(Genre genre);
    void delete(Genre genre);
    Genre getById(long id);
    List<Genre> getAll();
}
