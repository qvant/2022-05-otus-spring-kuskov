package ru.otus.spring.library.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CommentPageController {


    @GetMapping("/comments")
    public String listPage() {
        return "commentList";
    }

    @GetMapping("/commentCreate")
    public String createPage() {
        return "commentCreate";
    }

    @GetMapping("/commentDelete")
    public String deletePage() {
        return "commentDelete";
    }

    @GetMapping("/commentEdit")
    public String editPage() {
        return "commentEdit";
    }
}
