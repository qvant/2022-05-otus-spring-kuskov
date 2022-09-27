package ru.otus.spring.library.service;

import org.bson.types.ObjectId;

public interface BookService {
    void showBooks();

    void addBook(String title, ObjectId authorId, ObjectId genreId, String isbn);

    void updateBook(ObjectId id, String title, ObjectId authorId, ObjectId genreId, String isbn);

    void deleteBook(ObjectId id);

}
