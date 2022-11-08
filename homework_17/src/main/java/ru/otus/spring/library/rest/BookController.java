package ru.otus.spring.library.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("api/books/{id}")
    public Optional<BookDto> getBook(@PathVariable("id") Long id) {
        return bookService.findById(id).map(BookDto::toDto);
    }

    @PostMapping("api/books")
    public void saveBook(@RequestBody BookDto bookDto) {
        bookService.addBook(bookDto.getTitle(), bookDto.getAuthorId(), bookDto.getGenreId(), bookDto.getIsbn());
    }

    @PutMapping({"api/books", "api/books/{id}"})
    public void editABook(@RequestBody BookDto bookDto) {
        bookService.updateBook(bookDto.getId(), bookDto.getTitle(), bookDto.getAuthorId(), bookDto.getGenreId(), bookDto.getIsbn());
    }

    @DeleteMapping("api/books/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }


}
