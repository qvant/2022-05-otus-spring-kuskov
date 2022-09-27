package ru.otus.spring.library.service;

import org.bson.types.ObjectId;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import ru.otus.spring.library.domain.Book;
import ru.otus.spring.library.repository.AuthorRepository;
import ru.otus.spring.library.repository.BookRepository;
import ru.otus.spring.library.repository.GenreRepository;

import java.util.List;

@Component
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final IOService ioService;

    public BookServiceImpl(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository, IOService ioService) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
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
    public void addBook(String title, ObjectId authorId, ObjectId genreId, String isbn) {
        ioService.print("Автор с id " + authorId + " ");
        ioService.print("Жанр с id " + genreId + " ");
        var author = authorRepository.findById(authorId);
        if (author.isEmpty()) {
            ioService.print("Автор с id " + authorId + " не существует");
            return;
        }
        var genre = genreRepository.findById(genreId);
        if (genre.isEmpty()) {
            ioService.print("Жанр с id " + authorId + " не существует");
            return;
        }
        Book book = new Book(title, author.get(), genre.get(), isbn);
        bookRepository.save(book);
    }

    @Override
    public void updateBook(ObjectId id, String title, ObjectId authorId, ObjectId genreId, String isbn) {
        var author = authorRepository.findById(authorId);
        if (author.isEmpty()) {
            ioService.print("Автор с id " + authorId + " не существует");
            return;
        }
        var genre = genreRepository.findById(authorId);
        if (genre.isEmpty()) {
            ioService.print("Жанр с id " + authorId + " не существует");
            return;
        }
        Book book = new Book(id, title, author.get(), genre.get(), isbn);
        bookRepository.save(book);
    }

    @Override
    //@Transactional
    public void deleteBook(ObjectId id) {

        try {
            bookRepository.deleteById(id);
        } catch (
                EmptyResultDataAccessException exception) {
            ioService.print("Не существует книга с id " + id);
        }
    }


}
