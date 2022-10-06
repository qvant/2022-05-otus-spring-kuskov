package ru.otus.spring.library.service;

import org.springframework.stereotype.Component;
import ru.otus.spring.library.domain.Author;
import ru.otus.spring.library.repository.AuthorRepository;

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
        return authorRepository.findById(id).map(a -> {
            a.setName(name);
            authorRepository.save(a);
            return a;
        }).orElseGet(() ->
                {
                    ioService.print("Автор с id " + id + " не найден'");
                    return null;
                }
        );
    }

}
