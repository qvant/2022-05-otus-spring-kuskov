package ru.otus.spring.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.UnexpectedRollbackException;
import ru.otus.spring.library.service.BookService;
import ru.otus.spring.library.service.IOService;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {
    private final BookService bookService;
    private final IOService ioService;


    @ShellMethod(value = "Show books", key = {"b", "books"})
    public void showBooks() {
        bookService.showBooks();
    }

    @ShellMethod(value = "Add book", key = {"ba", "add_book"})
    public void addBook(@ShellOption String title, @ShellOption(defaultValue = "") Long author_id, @ShellOption(defaultValue = "") Long genre_id, @ShellOption(defaultValue = "") String isbn) {
        if (author_id == null) {
            ioService.print("Введите идентификатор автора");
            author_id = Long.parseLong(ioService.read());
        }
        if (genre_id == null) {
            ioService.print("Введите идентификатор жанра");
            genre_id = Long.parseLong(ioService.read());
        }
        if (isbn.length() == 0) {
            ioService.print("Введите ISBN");
            isbn = ioService.read();
        }
        bookService.addBook(title, author_id, genre_id, isbn);
    }

    @ShellMethod(value = "Update author", key = {"bu", "update_book"})
    public void updateBook(@ShellOption long id, @ShellOption String title, @ShellOption long author_id, @ShellOption long genre_id, @ShellOption(defaultValue = "") String isbn) {
        bookService.updateBook(id, title, author_id, genre_id, isbn);
    }

    @ShellMethod(value = "Delete book", key = {"bd", "delete_book"})
    public void deleteBook(@ShellOption long id) {
        try {
            bookService.deleteBook(id);
        } catch (UnexpectedRollbackException exception) {
        }
    }

}
