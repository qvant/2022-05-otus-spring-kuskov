package ru.otus.spring.library.service;

import org.springframework.stereotype.Component;
import ru.otus.spring.library.domain.Author;
import ru.otus.spring.library.repository.AuthorRepository;

import java.util.List;

@Component
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final IOService ioService;
    private final IdConverterService idConverterService;

    public AuthorServiceImpl(IOService ioService, AuthorRepository authorRepository, IdConverterService idConverterService) {
        this.authorRepository = authorRepository;
        this.ioService = ioService;
        this.idConverterService = idConverterService;
    }

    @Override
    public void showAuthors() {
        List<Author> authors = this.authorRepository.findAll();
        for (Author author : authors) {
            ioService.printWithParameters("[%s] %s", author.getId(), author.getName());
        }
    }

    @Override
    public Author addAuthor(String name) {
        Author author = new Author(name);
        authorRepository.save(author);
        return author;
    }

    @Override
    public Author updateAuthor(String id, String name) {
        var objectId = idConverterService.convertToObjectId(id);
        if (objectId == null) {
            return null;
        }
        var author = authorRepository.findById(objectId);
        if (author.isEmpty()) {
            ioService.print("Автор с id " + id + " не найден'");
            return null;
        }
        author.get().setName(name);
        authorRepository.save(author.get());
        return author.get();
    }

    @Override
    public void deleteAuthor(String id) {
        var objectId = idConverterService.convertToObjectId(id);
        if (objectId != null) {
            authorRepository.deleteById(objectId);
        }


    }
}
