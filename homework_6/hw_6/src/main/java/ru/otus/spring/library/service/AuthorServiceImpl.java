package ru.otus.spring.library.service;

import org.springframework.stereotype.Component;
import ru.otus.spring.library.domain.Author;
import ru.otus.spring.library.exceptions.HasDependentObjectsException;
import ru.otus.spring.library.repository.AuthorRepository;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final IOService ioService;

    public AuthorServiceImpl(IOService ioService, AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
        this.ioService = ioService;
    }

    @Override
    public void showAuthors() {
        List<Author> authors = this.authorRepository.findAll();
        for (Author author : authors) {
            ioService.printWithParameters("[%d] %s", author.getId(), author.getName());
        }
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
        Author author = new Author(id, "");
        try {
            authorRepository.deleteById(id);
        } catch (HasDependentObjectsException exception) {
            ioService.print(exception.getMessage());
        }

    }
}
