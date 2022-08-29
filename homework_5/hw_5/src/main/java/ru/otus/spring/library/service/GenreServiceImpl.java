package ru.otus.spring.library.service;

import org.springframework.stereotype.Component;
import ru.otus.spring.library.dao.GenreDao;
import ru.otus.spring.library.exceptions.HasDependentObjectsException;
import ru.otus.spring.library.domain.Genre;

import java.util.List;

@Component
public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDao;
    private final IOService ioService;

    public GenreServiceImpl(GenreDao genreDao, IOService ioService) {
        this.genreDao = genreDao;
        this.ioService = ioService;
    }

    @Override
    public void showGenres() {
        List<Genre> genres = this.genreDao.getAll();
        for (Genre genre : genres
        ) {
            ioService.printWithParameters("[%d] %s", genre.getId(), genre.getName());
        }
    }

    @Override
    public void addGenre(String name) {
        this.genreDao.insert(new Genre(name));
    }

    @Override
    public void updateGenre(long id, String name) {
        Genre genre = new Genre(id, name);
        genreDao.update(genre);
    }

    @Override
    public void deleteGenre(long id) {
        Genre genre = genreDao.getById(id);
        try {
            genreDao.delete(genre);
        } catch (
                HasDependentObjectsException exception) {
            ioService.print(exception.getMessage());
        }
    }
}
