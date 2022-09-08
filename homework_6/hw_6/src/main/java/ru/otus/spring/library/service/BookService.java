package ru.otus.spring.library.service;

public interface BookService {
    void showBooks();

    void addBook(String title, Long authorId, Long genreId, String isbn);

    void updateBook(long id, String title, long authorId, long genreId, String isbn);

    void deleteBook(long id);

}
