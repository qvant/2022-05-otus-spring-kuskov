package ru.otus.spring.library.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import ru.otus.spring.library.domain.Book;
import ru.otus.spring.library.domain.Genre;
import ru.otus.spring.library.repository.AuthorRepository;
import ru.otus.spring.library.repository.BookRepository;

import java.util.List;

@Component
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final IOService ioService;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, IOService ioService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.ioService = ioService;
    }


    @Override
    public void showBooks() {
        List<Book> books = this.bookRepository.findAll();
        for (Book book : books) {
            ioService.printWithParameters("[%s] %s %s (Жанр: %s) %S", book.getId(), book.getTitle(), book.getAuthor().getName(), book.getGenre().getName(), book.getIsbn());
        }
    }

    @Override
    public void addBook(String title, String authorId, String genreName, String isbn) {
        authorRepository.findById(authorId).map(author -> {
            Genre genre = new Genre(genreName);
            Book book = new Book(title, author, genre, isbn);
            bookRepository.save(book);
            return author;
        }).orElseGet(() -> {
            ioService.print("Автор с id " + authorId + " не существует");
            return null;
        });

    }

    @Override
    public void updateBook(String id, String title, String authorId, String genreName, String isbn) {
        authorRepository.findById(authorId).map(author -> {
            var genre = new Genre(genreName);
            Book book = new Book(id, title, author, genre, isbn);
            bookRepository.save(book);
            return author;
        }).orElseGet(() -> {
                    ioService.print("Автор с id " + authorId + " не существует");
                    return null;
                }
        );

    }

    @Override
    public void deleteBook(String id) {

        try {
            bookRepository.deleteById(id);
        } catch (
                EmptyResultDataAccessException exception) {
            ioService.print("Не существует книга с id " + id);
        }
    }


}
