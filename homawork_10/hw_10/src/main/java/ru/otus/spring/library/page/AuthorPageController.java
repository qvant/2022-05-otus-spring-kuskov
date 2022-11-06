package ru.otus.spring.library.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthorPageController {

    @GetMapping("/authors")
    public String listPage(Model model) {
        return "authorList";
    }

    @GetMapping("/authorEdit")
    public String editPage() {
        return "authorEdit";
    }

    @PostMapping("/authorEdit")
    public String authorEdit() {
        return "redirect:/authors";
    }

    @GetMapping("/authorCreate")
    public String createPage(Model model) {
        return "authorCreate";
    }


    @GetMapping("/authorDelete")
    public String deletePage() {
        return "authorDelete";
    }

    @PostMapping("/authorDelete")
    public String authorDelete() {
        return "redirect:/authors";
    }
}
