package ru.otus.spring.library.service;

import org.springframework.stereotype.Component;
import ru.otus.spring.library.domain.Genre;
import ru.otus.spring.library.exceptions.HasDependentObjectsException;
import ru.otus.spring.library.exceptions.IncorrectReferenceException;
import ru.otus.spring.library.repository.GenreRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
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
            genreRepository.deleteByIdWithDependencyException(id);
        } catch (HasDependentObjectsException exception) {
            throw new IncorrectReferenceException("Нельзя удалить жанр с id " + id + ", есть зависимости");
        }
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> findById(long id) {
        return genreRepository.findById(id);
    }
}
