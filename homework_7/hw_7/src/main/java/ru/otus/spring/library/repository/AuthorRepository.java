package ru.otus.spring.library.repository;

import ru.otus.spring.library.domain.Author;
import ru.otus.spring.library.exceptions.HasDependentObjectsException;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    List<Author> findAll();

    Optional<Author> findById(Long id);

    Author save(Author author);

    void deleteById(Long id) throws HasDependentObjectsException;
}
