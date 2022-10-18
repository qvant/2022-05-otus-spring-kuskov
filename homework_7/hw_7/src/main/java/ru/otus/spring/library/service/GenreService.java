package ru.otus.spring.library.service;

public interface GenreService {
    void showGenres();

    void addGenre(String name);

    void updateGenre(long id, String name);

    void deleteGenre(long id);
}
