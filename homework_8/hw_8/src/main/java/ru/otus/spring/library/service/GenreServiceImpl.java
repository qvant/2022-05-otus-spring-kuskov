package ru.otus.spring.library.service;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import ru.otus.spring.library.domain.Genre;
import ru.otus.spring.library.repository.GenreRepository;

import java.util.List;

@Component
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final IOService ioService;
    private final IdConverterService idConverterService;

    public GenreServiceImpl(GenreRepository genreRepository, IOService ioService, IdConverterService idConverterService) {
        this.genreRepository = genreRepository;
        this.ioService = ioService;
        this.idConverterService = idConverterService;
    }

    @Override
    public void showGenres() {
        List<Genre> genres = this.genreRepository.findAll();
        for (Genre genre : genres
        ) {
            ioService.printWithParameters("[%s] %s", genre.getId(), genre.getName());
        }
    }


    @Override
    public void addGenre(String name) {
        this.genreRepository.save(new Genre(name));
    }

    @Override
    public void updateGenre(String id, String name) {
        ObjectId objectId = idConverterService.convertToObjectId(id);
        if (objectId == null) {
            return;
        }
        var genre = genreRepository.findById(objectId);
        if (genre.isEmpty()) {
            ioService.print("Жанр с id " + id + " не найден");
            return;
        }
        genre.get().setName(name);
        genreRepository.save(genre.get());
    }

    @Override
    public void deleteGenre(String id) {
        ObjectId objectId = idConverterService.convertToObjectId(id);
        if (objectId == null) {
            return;
        }
        genreRepository.deleteById(objectId);

    }
}
