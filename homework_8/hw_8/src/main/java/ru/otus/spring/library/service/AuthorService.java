package ru.otus.spring.library.service;

import org.bson.types.ObjectId;
import ru.otus.spring.library.domain.Author;

public interface AuthorService {
    void showAuthors();

    Author addAuthor(String name);

    Author updateAuthor(String id, String name);

    void deleteAuthor(String id);
}
