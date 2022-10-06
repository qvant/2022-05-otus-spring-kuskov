package ru.otus.spring.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
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
    public void addBook(@ShellOption String title, @ShellOption(defaultValue = "") String author_id, @ShellOption(defaultValue = "") String genre, @ShellOption(defaultValue = "") String isbn) {
        if (author_id == null) {
            ioService.print("Введите идентификатор автора");
            author_id = ioService.read();
        }
        if (genre == null) {
            ioService.print("Введите жанр");
            genre = ioService.read();
        }
        if (isbn.length() == 0) {
            ioService.print("Введите ISBN");
            isbn = ioService.read();
        }
        bookService.addBook(title, author_id, genre, isbn);
    }

    @ShellMethod(value = "Update book", key = {"bu", "update_book"})
    public void updateBook(@ShellOption String id, @ShellOption String title, @ShellOption String author_id, @ShellOption String genre, @ShellOption(defaultValue = "") String isbn) {
        bookService.updateBook(id, title, author_id, genre, isbn);
    }

    @ShellMethod(value = "Delete book", key = {"bd", "delete_book"})
    public void deleteBook(@ShellOption String id) {
        bookService.deleteBook(id);
    }

}
