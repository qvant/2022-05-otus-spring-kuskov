package ru.otus.spring.library.service;

import ru.otus.spring.library.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    void addGenre(String name);

    void updateGenre(long id, String name);

    void deleteGenre(long id);

    List<Genre> findAll();

    Optional<Genre> findById(long id);
}
