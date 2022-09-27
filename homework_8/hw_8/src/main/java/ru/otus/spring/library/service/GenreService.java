package ru.otus.spring.library.service;

import org.bson.types.ObjectId;

public interface GenreService {
    void showGenres();

    void addGenre(String name);

    void updateGenre(String id, String name);

    void deleteGenre(String id);
}
