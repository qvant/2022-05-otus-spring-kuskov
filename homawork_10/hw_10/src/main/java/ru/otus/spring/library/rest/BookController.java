package ru.otus.spring.library.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.spring.library.dto.BookDto;
import ru.otus.spring.library.service.BookService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("api/books")
    public List<BookDto> getAllBooks() {
        return bookService.findAll().stream().map(BookDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("api/book")
    public Optional<BookDto> getBook(Long id) {
        return bookService.findById(id).map(BookDto::toDto);
    }

    @PostMapping("api/bookEdit")
    public RedirectView saveBook(BookDto bookDto) {
        if (bookDto.getId() == null) {
            bookService.addBook(bookDto.getTitle(), bookDto.getAuthorId(), bookDto.getGenreId(), bookDto.getIsbn());
        } else {
            bookService.updateBook(bookDto.getId(), bookDto.getTitle(), bookDto.getAuthorId(), bookDto.getGenreId(), bookDto.getIsbn());
        }
        return new RedirectView("/books");
    }

    @PostMapping("api/bookDelete")
    public RedirectView deleteBook(Long id) {
        bookService.deleteBook(id);
        return new RedirectView("/books");
    }


}
