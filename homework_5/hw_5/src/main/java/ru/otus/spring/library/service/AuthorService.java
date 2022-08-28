package ru.otus.spring.library.service;

import ru.otus.spring.library.domain.Author;

public interface AuthorService {
    public void showAuthors();

    public Author addAuthor(String name);

    public void updateAuthor(long id, String name);

    public void deleteAuthor(long id);
}
