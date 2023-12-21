package ru.otus.spring.library.service;

import ru.otus.spring.library.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Author addAuthor(String name);

    Author updateAuthor(long id, String name);

    void deleteAuthor(long id);

    List<Author> findAll();

    Optional<Author> findById(long id);
}
