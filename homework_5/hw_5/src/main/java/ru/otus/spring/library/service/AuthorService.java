package ru.otus.spring.library.service;

import ru.otus.spring.library.domain.Author;

public interface AuthorService {
    void showAuthors();

    Author addAuthor(String name);

    Author updateAuthor(long id, String name);

    void deleteAuthor(long id);
}
