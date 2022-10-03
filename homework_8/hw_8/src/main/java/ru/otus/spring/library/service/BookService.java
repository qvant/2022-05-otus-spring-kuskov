package ru.otus.spring.library.service;

public interface BookService {
    void showBooks();

    void addBook(String title, String authorId, String genreName, String isbn);

    void updateBook(String id, String title, String authorId, String genreName, String isbn);

    void deleteBook(String id);

}
