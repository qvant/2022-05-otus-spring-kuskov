package ru.otus.spring.library.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.library.domain.Author;
import ru.otus.spring.library.exceptions.HasDependentObjectsException;
import ru.otus.spring.library.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Component
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    @Transactional
    public Author addAuthor(String name) {
        Author author = new Author(name);
        authorRepository.save(author);
        return author;
    }

    @Override
    @Transactional
    public Author updateAuthor(long id, String name) {
        Author author = new Author(id, name);
        authorRepository.save(author);
        return author;
    }

    @Override
    @Transactional
    public void deleteAuthor(long id) {
        try {
            authorRepository.deleteByIdWithDependencyException(id);
        } catch (HasDependentObjectsException exception) {
            throw new RuntimeException("Нельзя удалить автора с id " + id + ", есть зависимости");
        }

    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(long id) {
        return authorRepository.findById(id);
    }
}
