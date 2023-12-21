package ru.otus.spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.library.domain.Comment;
import ru.otus.spring.library.service.CommentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/comments")
    public String listPage(Model model, @RequestParam("book_id") Long bookId) {
        List<Comment> comments = commentService.findByBookId(bookId);
        model.addAttribute("comments", comments);
        model.addAttribute("book_id", bookId);
        return "commentList";
    }

    @GetMapping("/commentCreate")
    public String createPage(Model model, @RequestParam("book_id") Long bookId) {
        model.addAttribute("book_id", bookId);
        return "commentCreate";
    }

    @PostMapping("/commentCreate")
    public String createComment(@RequestParam("book_id") Long bookId, @RequestParam("text") String text) {
        commentService.addComment(bookId, text);
        return "redirect:/comments?book_id=" + bookId;
    }

    @GetMapping("/commentDelete")
    public String deletePage(Model model, @RequestParam("id") Long id) {
        Comment comment = commentService.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("id", id);
        model.addAttribute("comment", comment);
        model.addAttribute("book_id", comment.getBook().getId());
        return "commentDelete";
    }

    @PostMapping("/commentDelete")
    public String commentDelete(
            @RequestParam("id") long id
    ) {
        long bookId = commentService.findById(id).orElseThrow(NotFoundException::new).getBook().getId();
        commentService.deleteComment(id);
        return "redirect:/comments?book_id=" + bookId;
    }

    @GetMapping("/commentEdit")
    public String editPage(Model model, @RequestParam("id") Long id) {
        Comment comment = commentService.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("id", id);
        model.addAttribute("comment", comment);
        model.addAttribute("book_id", comment.getBook().getId());
        return "commentEdit";
    }

    @PostMapping("/commentEdit")
    public String commentEdit(
            @RequestParam("id") long id,
            @RequestParam("text") String text
    ) {
        Comment comment = commentService.findById(id).orElseThrow(NotFoundException::new);
        commentService.updateComment(id, text);
        return "redirect:/comments?book_id=" + comment.getBook().getId();
    }
}
