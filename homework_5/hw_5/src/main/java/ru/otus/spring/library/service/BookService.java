package ru.otus.spring.library.service;

public interface BookService {
    void showBooks();

    void addBook(String title, Long author_id, Long genre_id, String isbn);

    void updateBook(long id, String title, long author_id, long genre_id, String isbn);

    void deleteBook(long id);

}
