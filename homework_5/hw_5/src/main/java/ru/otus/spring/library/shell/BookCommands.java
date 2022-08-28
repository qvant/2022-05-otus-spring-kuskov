package ru.otus.spring.library.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.library.service.BookService;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {
    private final BookService bookService;


    @ShellMethod(value = "Show books", key = {"b", "books"})
    public void showBooks() {
        bookService.showBooks();
    }

    @ShellMethod(value = "Add book", key = {"ba", "add_book"})
    public void addBook(@ShellOption String title, @ShellOption(defaultValue = "") Long author_id, @ShellOption(defaultValue = "") Long genre_id, @ShellOption(defaultValue = "") String isbn){
        bookService.addBook(title, author_id, genre_id, isbn);
    }

    @ShellMethod(value = "Update author", key = {"bu", "update_book"})
    public void updateBook(@ShellOption long id, @ShellOption String title, @ShellOption long author_id, @ShellOption long genre_id, @ShellOption(defaultValue = "") String isbn) {
        bookService.updateBook(id, title, author_id, genre_id, isbn);
    }

    @ShellMethod(value = "Delete book", key = {"bd", "delete_book"})
    public void deleteAuthor(@ShellOption long id) {
        bookService.deleteBook(id);
    }

}
