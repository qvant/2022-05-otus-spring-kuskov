package ru.otus.spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.library.domain.Author;
import ru.otus.spring.library.service.AuthorService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/authors")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String listPage(Model model) {
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "authorList";
    }

    @GetMapping("/authorEdit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Author author = authorService.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("author", author);
        return "authorEdit";
    }

    @PostMapping("/authorEdit")
    public String authorEdit(@RequestParam("id") Long id, @RequestParam("name") String name) {
        authorService.updateAuthor(id, name);
        return "redirect:/authors";
    }

    @GetMapping("/authorCreate")
    public String createPage(Model model) {
        return "authorCreate";
    }

    @PostMapping("/authorCreate")
    public String authorCreate(@RequestParam("name") String name) {
        authorService.addAuthor(name);
        return "redirect:/authors";
    }

    @GetMapping("/authorDelete")
    public String deletePage(@RequestParam("id") long id, Model model) {
        Author author = authorService.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("author", author);
        return "authorDelete";
    }

    @PostMapping("/authorDelete")
    public String authorDelete(@RequestParam("id") Long id) {
        authorService.deleteAuthor(id);
        return "redirect:/authors";
    }
}
