package ru.otus.spring.library.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.library.domain.Author;
import ru.otus.spring.library.dto.AuthorDto;
import ru.otus.spring.library.service.AuthorService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("api/authors")
    public List<AuthorDto> getAllAuthors() {
        return authorService.findAll().stream().map(AuthorDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("api/authors/{id}")
    public Optional<AuthorDto> getAuthor(@PathVariable("id") Long id) {
        return authorService.findById(id).map(AuthorDto::toDto);
    }

    @PostMapping("api/authors")
    public void saveAuthor(@RequestBody AuthorDto authorDto) {
        Author author = authorDto.toDomain();
        authorService.addAuthor(author.getName());
    }

    @PutMapping({"api/authors", "api/authors/{id}"})
    public void editAuthor(@RequestBody AuthorDto authorDto) {
        Author author = authorDto.toDomain();
        authorService.updateAuthor(author.getId(), author.getName());
    }

    @DeleteMapping("api/authors/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }


}
