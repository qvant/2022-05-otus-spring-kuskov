package ru.otus.spring.library.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class BookPageController {

    @GetMapping("/books")
    public String listPage(Model model) {
        return "bookList";
    }

    @GetMapping("/bookEdit")
    public String editPage(@RequestParam("id") long id, Model model) {
        return "bookEdit";
    }


    @GetMapping("/bookCreate")
    public String createPage() {
        return "bookCreate";
    }


    @GetMapping("/bookDelete")
    public String deletePage() {
        return "bookDelete";
    }

}
