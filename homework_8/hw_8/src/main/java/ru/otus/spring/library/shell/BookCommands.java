package ru.otus.spring.library.shell;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.library.service.BookService;
import ru.otus.spring.library.service.IOService;
import ru.otus.spring.library.service.IdConverterService;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {
    private final BookService bookService;
    private final IOService ioService;
    private final IdConverterService idConverterService;


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
        authorId = idConverterService.convertToObjectId(author_id);
        if (authorId == null) {
            return;
        }
        if (genre_id == null) {
            ioService.print("Введите идентификатор жанра");
            genre_id = ioService.read();
        }
        genreId = idConverterService.convertToObjectId(genre_id);
        if (genreId == null) {
            return;
        }
        if (isbn.length() == 0) {
            ioService.print("Введите ISBN");
            isbn = ioService.read();
        }
        bookService.addBook(title, authorId, genreId, isbn);
    }

    @ShellMethod(value = "Update author", key = {"bu", "update_book"})
    public void updateBook(@ShellOption ObjectId id, @ShellOption String title, @ShellOption ObjectId author_id, @ShellOption ObjectId genre_id, @ShellOption(defaultValue = "") String isbn) {
        bookService.updateBook(id, title, author_id, genre_id, isbn);
    }

    @ShellMethod(value = "Delete book", key = {"bd", "delete_book"})
    public void deleteBook(@ShellOption String id) {
        ObjectId bookId = idConverterService.convertToObjectId(id);
        if (bookId == null) {
            return;
        }
        bookService.deleteBook(bookId);
    }

}
