package ru.otus.spring.library.service;

public interface BookService {
    public void showBooks();

    public void addBook(String title, Long author_id, Long genre_id, String isbn);

    public void updateBook(long id, String title, long author_id, long genre_id, String isbn);

    public void deleteBook(long id);

}
