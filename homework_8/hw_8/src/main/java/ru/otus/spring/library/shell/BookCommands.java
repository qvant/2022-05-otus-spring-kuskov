package ru.otus.spring.library.shell;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
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
    public void addBook(@ShellOption String title, @ShellOption(defaultValue = "") String author_id, @ShellOption(defaultValue = "") String genre_id, @ShellOption(defaultValue = "") String isbn) {
        ObjectId genreId, authorId;
        if (author_id == null) {
            ioService.print("Введите идентификатор автора");
            author_id = ioService.read();
        }
        try {
            authorId = new ObjectId(author_id);
        } catch (Exception exception) {
            ioService.print("Неверный идентификатор автора");
            return;
        }
        if (genre_id == null) {
            ioService.print("Введите идентификатор жанра");
            genre_id = ioService.read();
        }
        try {
            genreId = new ObjectId(genre_id);
        } catch (Exception exception) {
            ioService.print("Неверный идентификатор жанра");
            return;
        }
        if (isbn.length() == 0) {
            ioService.print("Введите ISBN");
            isbn = ioService.read();
        }
        ioService.print("Неверный  автора" + authorId);
        ioService.print("Неверный жанра " + genreId);
        bookService.addBook(title, authorId, genreId, isbn);
    }

    @ShellMethod(value = "Update author", key = {"bu", "update_book"})
    public void updateBook(@ShellOption ObjectId id, @ShellOption String title, @ShellOption ObjectId author_id, @ShellOption ObjectId genre_id, @ShellOption(defaultValue = "") String isbn) {
        bookService.updateBook(id, title, author_id, genre_id, isbn);
    }

    @ShellMethod(value = "Delete book", key = {"bd", "delete_book"})
    public void deleteBook(@ShellOption String id) {
        try {
            bookService.deleteBook(new ObjectId(new String(id)));
            //bookService.deleteBook(new ObjectId(id));
        } catch (IllegalArgumentException exception) {
        }
    }

}
