package ru.otus.spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.library.domain.Author;
import ru.otus.spring.library.exceptions.HasDependentObjectsException;
import ru.otus.spring.library.repository.AuthorRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorRepository authorRepository;

    @GetMapping("/authors")
    public String listPage(Model model) {
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);
        return "authorList";
    }

    @GetMapping("/authorEdit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Author author = authorRepository.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("author", author);
        return "authorEdit";
    }

    @GetMapping("/authorCreate")
    public String createPage(Model model) {
        return "authorCreate";
    }

    @GetMapping("/authorDelete")
    public String deletePage(@RequestParam("id") long id, Model model) {
        Author author = authorRepository.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("author", author);
        return "authorDelete";
    }

    @PostMapping("/authorEdit")
    public String authorEdit(Author author, Model model) {
        authorRepository.save(author);
        return "redirect:/authors";
    }

    @PostMapping("/authorCreate")
    public String authorCreate(@RequestParam("name") String name) {
        Author author = new Author(name);
        authorRepository.save(author);
        return "redirect:/authors";
    }

    @PostMapping("/authorDelete")
    @Transactional
    public String authorDelete(Author author) {
        try {
            authorRepository.deleteByIdWithDependencyException(author.getId());
        } catch (HasDependentObjectsException exception) {
            throw new RuntimeException(exception.getMessage());
        }

        return "redirect:/authors";
    }
}
