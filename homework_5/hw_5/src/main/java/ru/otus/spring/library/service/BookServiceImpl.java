package ru.otus.spring.library.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import ru.otus.spring.library.dao.AuthorDao;
import ru.otus.spring.library.dao.BookDao;
import ru.otus.spring.library.dao.GenreDao;
import ru.otus.spring.library.domain.Author;
import ru.otus.spring.library.domain.Book;
import ru.otus.spring.library.domain.Genre;

import java.util.List;

@Component
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final IOService ioService;

    public BookServiceImpl(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao, IOService ioService) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.ioService = ioService;
    }


    @Override
    public void showBooks() {
        List<Book> books = this.bookDao.getAll();
        for (Book book : books) {
            ioService.printWithParameters("[%d] %s %s (Жанр: %s) %S", book.getId(), book.getTitle(), book.getAuthor().getName(), book.getGenre().getName(), book.getIsbn());
        }
    }

    @Override
    public void addBook(String title, Long author_id, Long genre_id, String isbn) {
        Author author = null;
        Genre genre = null;
        try {
            author = authorDao.getById(author_id);
        } catch (EmptyResultDataAccessException exception) {
            ioService.print("Автор с id " + author_id + " не существует");
            return;
        }
        try {
            genre = genreDao.getById(author_id);
        } catch (EmptyResultDataAccessException exception) {
            ioService.print("Жанр с id " + author_id + " не существует");
            return;
        }
        Book book = new Book(title, author, genre, isbn);
        bookDao.insert(book);
    }

    @Override
    public void updateBook(long id, String title, long author_id, long genre_id, String isbn) {
        Author author = null;
        Genre genre = null;
        try {
            author = authorDao.getById(author_id);
        } catch (EmptyResultDataAccessException exception) {
            ioService.print("Автор с id " + author_id + "не существует");
            return;
        }
        try {
            genre = genreDao.getById(author_id);
        } catch (EmptyResultDataAccessException exception) {
            ioService.print("Жанр с id " + author_id + "не существует");
            return;
        }
        Book book = new Book(id, title, author, genre, isbn);
        bookDao.update(book);
    }

    @Override
    public void deleteBook(long id) {
        Book book = this.bookDao.getById(id);
        this.bookDao.delete(book);
    }


}
