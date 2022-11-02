package ru.otus.spring.library.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring.library.service.CommentService;

@Controller
@RequiredArgsConstructor
public class CommentPageController {

    private final CommentService commentService;

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
