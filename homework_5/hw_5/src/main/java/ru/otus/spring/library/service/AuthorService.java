package ru.otus.spring.library.service;

public interface AuthorService {
    public void showAuthors();
    public void addAuthor(String name);
    public void updateAuthor(long id, String name);
    public void deleteAuthor(long id);
}
