package ru.otus.spring.library.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class GenrePageController {

    @GetMapping("/genres")
    public String listPage() {
        return "genreList";
    }

    @GetMapping("/genreEdit")
    public String editPage() {
        return "genreEdit";
    }

    @GetMapping("/genreCreate")
    public String createPage() {
        return "genreCreate";
    }

    @GetMapping("/genreDelete")
    public String deletePage() {
        return "genreDelete";
    }


}
