package ru.otus.spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.library.domain.Genre;
import ru.otus.spring.library.service.GenreService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class GenreController {
    private final GenreService genreService;

    @GetMapping("/genres")
    public String listPage(Model model) {
        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);
        return "genreList";
    }

    @GetMapping("/genreEdit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Genre genre = genreService.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("genre", genre);
        return "genreEdit";
    }

    @GetMapping("/genreCreate")
    public String createPage(Model model) {
        return "genreCreate";
    }

    @GetMapping("/genreDelete")
    public String deletePage(@RequestParam("id") long id, Model model) {
        Genre genre = genreService.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("genre", genre);
        return "genreDelete";
    }

    @PostMapping("/genreEdit")
    public String genreEdit(@RequestParam("id") long id, @RequestParam("name") String name) {
        genreService.updateGenre(id, name);
        return "redirect:/genres";
    }

    @PostMapping("/genreCreate")
    public String genreCreate(@RequestParam("name") String name) {
        genreService.addGenre(name);
        return "redirect:/genres";
    }

    @PostMapping("/genreDelete")
    public String genreDelete(@RequestParam("id") Long id) {
        genreService.deleteGenre(id);
        return "redirect:/genres";
    }

}
