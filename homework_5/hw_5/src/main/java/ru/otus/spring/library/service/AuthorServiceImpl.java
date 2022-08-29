package ru.otus.spring.library.service;

import org.springframework.stereotype.Component;
import ru.otus.spring.library.dao.AuthorDao;
import ru.otus.spring.library.exceptions.HasDependentObjectsException;
import ru.otus.spring.library.domain.Author;

import java.util.List;

@Component
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;
    private final IOService ioService;

    public AuthorServiceImpl(AuthorDao authorDao, IOService ioService) {
        this.authorDao = authorDao;
        this.ioService = ioService;
    }

    @Override
    public void showAuthors() {
        List<Author> authors = this.authorDao.getAll();
        for (Author author : authors
        ) {
            ioService.printWithParameters("[%d] %s", author.getId(), author.getName());
        }
    }

    @Override
    public Author addAuthor(String name) {
        Author author = new Author(name);
        authorDao.insert(author);
        return author;
    }

    @Override
    public void updateAuthor(long id, String name) {
        Author author = new Author(id, name);
        authorDao.update(author);
    }

    @Override
    public void deleteAuthor(long id) {
        Author author = authorDao.getById(id);
        try {
            authorDao.delete(author);
        } catch (HasDependentObjectsException exception){
            ioService.print(exception.getMessage());
        }

    }
}
