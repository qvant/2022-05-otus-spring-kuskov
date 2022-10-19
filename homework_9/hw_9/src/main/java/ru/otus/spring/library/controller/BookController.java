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
import ru.otus.spring.library.repository.AuthorRepository;
import ru.otus.spring.library.repository.BookRepository;
import ru.otus.spring.library.repository.GenreRepository;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BookController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @GetMapping("/books")
    public String listPage(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "bookList";
    }

    @GetMapping("/bookEdit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow(NotFoundException::new);
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);
        List<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);
        model.addAttribute("book", book);
        return "bookEdit";
    }

    @GetMapping("/bookCreate")
    public String createPage(Model model) {
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);
        List<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);
        return "bookCreate";
    }

    @GetMapping("/bookDelete")
    public String deletePage(@RequestParam("id") long id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        model.addAttribute("author", book.getAuthor().getName());
        model.addAttribute("genre", book.getGenre().getName());
        return "bookDelete";
    }

    @PostMapping("/bookEdit")
    public String bookEdit(Book book,
                           @RequestParam("author_id") Long authorId,
                           @RequestParam("genre_id") Long genreId) {
        Author author = authorRepository.findById(authorId).orElseThrow(NotFoundException::new);
        Genre genre = genreRepository.findById(genreId).orElseThrow(NotFoundException::new);
        book.setAuthor(author);
        book.setGenre(genre);
        bookRepository.save(book);
        return "redirect:/books";
    }

    @PostMapping("/bookCreate")
    public String bookCreate(@RequestParam("title") String title, @RequestParam("isbn") String isbn,
                             @RequestParam("author_id") Long authorId,
                             @RequestParam("genre_id") Long genreId) {
        Author author = authorRepository.findById(authorId).orElseThrow(NotFoundException::new);
        Genre genre = genreRepository.findById(genreId).orElseThrow(NotFoundException::new);
        Book book = new Book(title, author, genre, isbn);
        bookRepository.save(book);
        return "redirect:/books";
    }

    @PostMapping("/bookDelete")
    public String bookDelete(
            @RequestParam("id") long id
    ) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }

}
