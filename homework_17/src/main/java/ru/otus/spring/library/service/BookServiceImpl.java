package ru.otus.spring.library.service;

import org.springframework.stereotype.Component;
import ru.otus.spring.library.domain.Author;
import ru.otus.spring.library.domain.Book;
import ru.otus.spring.library.domain.Genre;
import ru.otus.spring.library.exceptions.IncorrectReferenceException;
import ru.otus.spring.library.repository.AuthorRepository;
import ru.otus.spring.library.repository.BookRepository;
import ru.otus.spring.library.repository.GenreRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }


    @Transactional
    @Override
    public void addBook(String title, Long authorId, Long genreId, String isbn) {
        Author author = authorRepository.findById(authorId).orElseThrow(IncorrectReferenceException::new);
        Genre genre = genreRepository.findById(genreId).orElseThrow(IncorrectReferenceException::new);
        Book book = new Book(title, author, genre, isbn);
        bookRepository.save(book);
    }

    @Transactional
    @Override
    public void updateBook(long id, String title, long authorId, long genreId, String isbn) {
        Author author = authorRepository.findById(authorId).orElseThrow(IncorrectReferenceException::new);
        Genre genre = genreRepository.findById(genreId).orElseThrow(IncorrectReferenceException::new);
        Book book = new Book(id, title, author, genre, isbn);
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteBook(long id) {

        bookRepository.deleteById(id);

    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(long id) {
        return bookRepository.findById(id);
    }


}
