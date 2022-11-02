package ru.otus.spring.library.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
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

    @GetMapping("api/author")
    public Optional<AuthorDto> getAuthor(Long id) {
        return authorService.findById(id).map(AuthorDto::toDto);
    }

    @PostMapping("api/authorEdit")
    public RedirectView saveAuthor(AuthorDto authorDto) {
        Author author = authorDto.toDomain();
        if (author.getId() == null) {
            authorService.addAuthor(author.getName());
        } else {
            authorService.updateAuthor(author.getId(), author.getName());
        }
        return new RedirectView("/authors");
    }

    @PostMapping("api/authorDelete")
    public RedirectView deleteAuthor(Long id) {
        authorService.deleteAuthor(id);
        return new RedirectView("/authors");
    }


}
