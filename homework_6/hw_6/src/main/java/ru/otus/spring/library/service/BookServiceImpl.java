package ru.otus.spring.library.service;

import org.springframework.stereotype.Component;
import ru.otus.spring.library.domain.Book;
import ru.otus.spring.library.repository.AuthorRepository;
import ru.otus.spring.library.repository.BookRepository;
import ru.otus.spring.library.repository.GenreRepository;

import javax.transaction.Transactional;
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
            ioService.printWithParameters("[%d] %s %s (Жанр: %s) %S", book.getId(), book.getTitle(), book.getAuthor().getName(), book.getGenre().getName(), book.getIsbn());
        }
    }

    @Transactional
    @Override
    public void addBook(String title, Long author_id, Long genre_id, String isbn) {
        var author = authorRepository.findById(author_id);
        if (author.isEmpty()) {
            ioService.print("Автор с id " + author_id + " не существует");
            return;
        }
        var genre = genreRepository.findById(author_id);
        if (genre.isEmpty()) {
            ioService.print("Жанр с id " + author_id + " не существует");
            return;
        }
        Book book = new Book(title, author.get(), genre.get(), isbn);
        bookRepository.save(book);
    }

    @Transactional
    @Override
    public void updateBook(long id, String title, long author_id, long genre_id, String isbn) {
        var author = authorRepository.findById(author_id);
        if (author.isEmpty()) {
            ioService.print("Автор с id " + author_id + " не существует");
            return;
        }
        var genre = genreRepository.findById(author_id);
        if (genre.isEmpty()) {
            ioService.print("Жанр с id " + author_id + " не существует");
            return;
        }
        Book book = new Book(id, title, author.get(), genre.get(), isbn);
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }


}
