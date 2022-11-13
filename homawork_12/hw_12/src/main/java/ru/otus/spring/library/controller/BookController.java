package ru.otus.spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.library.domain.Author;
import ru.otus.spring.library.domain.Book;
import ru.otus.spring.library.domain.Genre;
import ru.otus.spring.library.service.AuthorService;
import ru.otus.spring.library.service.BookService;
import ru.otus.spring.library.service.GenreService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @GetMapping("/books")
    public String listPage(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "bookList";
    }

    @GetMapping("/bookEdit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Book book = bookService.findById(id).orElseThrow(NotFoundException::new);
        List<Author> authors = authorService.findAll();
        List<Genre> genres = genreService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        model.addAttribute("book", book);
        return "bookEdit";
    }

    @PostMapping("/bookEdit")
    public String bookEdit(@RequestParam("id") Long id, @RequestParam("title") String title, @RequestParam("isbn") String isbn, @RequestParam("author_id") Long authorId, @RequestParam("genre_id") Long genreId) {
        bookService.updateBook(id, title, authorId, genreId, isbn);
        return "redirect:/books";
    }

    @GetMapping("/bookCreate")
    public String createPage(Model model) {
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);
        return "bookCreate";
    }

    @PostMapping("/bookCreate")
    public String bookCreate(@RequestParam("title") String title, @RequestParam("isbn") String isbn, @RequestParam("author_id") Long authorId, @RequestParam("genre_id") Long genreId) {
        bookService.addBook(title, authorId, genreId, isbn);
        return "redirect:/books";
    }

    @GetMapping("/bookDelete")
    public String deletePage(@RequestParam("id") long id, Model model) {
        Book book = bookService.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        model.addAttribute("author", book.getAuthor().getName());
        model.addAttribute("genre", book.getGenre().getName());
        return "bookDelete";
    }


    @PostMapping("/bookDelete")
    public String bookDelete(@RequestParam("id") long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

}
