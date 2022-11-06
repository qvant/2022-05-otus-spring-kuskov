package ru.otus.spring.library.service;

import org.springframework.stereotype.Component;
import ru.otus.spring.library.domain.Genre;
import ru.otus.spring.library.exceptions.HasDependentObjectsException;
import ru.otus.spring.library.repository.GenreRepository;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final IOService ioService;

    public GenreServiceImpl(GenreRepository genreRepository, IOService ioService) {
        this.genreRepository = genreRepository;
        this.ioService = ioService;
    }

    @Override
    public void showGenres() {
        List<Genre> genres = this.genreRepository.findAll();
        for (Genre genre : genres
        ) {
            ioService.printWithParameters("[%d] %s", genre.getId(), genre.getName());
        }
    }


    @Transactional
    @Override
    public void addGenre(String name) {
        this.genreRepository.save(new Genre(name));
    }

    @Transactional
    @Override
    public void updateGenre(long id, String name) {
        Genre genre = new Genre(id, name);
        genreRepository.save(genre);
    }

    @Transactional
    @Override
    public void deleteGenre(long id) {
        try {
            genreRepository.deleteById(id);
        } catch (HasDependentObjectsException exception) {
            ioService.print("Нельзя удалить жанр с id " + id + ", есть зависимости");
        }
    }
}
