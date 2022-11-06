package ru.otus.spring.library.repository;

import ru.otus.spring.library.domain.Genre;
import ru.otus.spring.library.exceptions.HasDependentObjectsException;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    List<Genre> findAll();

    Optional<Genre> findById(Long id);

    Genre save(Genre genre);

    void deleteById(Long id) throws HasDependentObjectsException;
}
