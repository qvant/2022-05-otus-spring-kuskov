package ru.otus.spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.library.domain.Genre;
import ru.otus.spring.library.exceptions.HasDependentObjectsException;
import ru.otus.spring.library.repository.GenreRepository;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class GenreController {
    private final GenreRepository genreRepository;

    @GetMapping("/genres")
    public String listPage(Model model) {
        List<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);
        return "genreList";
    }

    @GetMapping("/genreEdit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Genre genre = genreRepository.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("genre", genre);
        return "genreEdit";
    }

    @GetMapping("/genreCreate")
    public String createPage(Model model) {
        return "genreCreate";
    }

    @GetMapping("/genreDelete")
    public String deletePage(@RequestParam("id") long id, Model model) {
        Genre genre = genreRepository.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("genre", genre);
        return "genreDelete";
    }

    @PostMapping("/genreEdit")
    public String genreEdit(Genre genre) {
        genreRepository.save(genre);
        return "redirect:/genres";
    }

    @PostMapping("/genreCreate")
    public String genreCreate(@RequestParam("name") String name) {
        Genre genre = new Genre(name);
        genreRepository.save(genre);
        return "redirect:/genres";
    }

    @PostMapping("/genreDelete")
    @Transactional
    public String genreDelete(Genre genre) {
        try {
            genreRepository.deleteByIdWithDependencyException(genre.getId());
        } catch (HasDependentObjectsException exception) {
            throw new RuntimeException(exception.getMessage());
        }

        return "redirect:/genres";
    }

}
