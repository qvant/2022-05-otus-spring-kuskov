package ru.otus.spring.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.library.domain.Book;
import ru.otus.spring.library.domain.Comment;
import ru.otus.spring.library.domain.Genre;
import ru.otus.spring.library.repository.BookRepository;
import ru.otus.spring.library.repository.CommentRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @GetMapping("/comments")
    public String listPage(Model model, @RequestParam("book_id") Long bookId) {
        List<Comment> comments = commentRepository.findByBookId(bookId);
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
        Book book = bookRepository.findById(bookId).orElseThrow(NotFoundException::new);
        Comment comment = new Comment(book, text);
        commentRepository.save(comment);
        return "redirect:/comments?book_id=" + bookId;
    }

    @GetMapping("/commentDelete")
    public String deletePage(Model model, @RequestParam("id") Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("id", id);
        model.addAttribute("comment", comment);
        model.addAttribute("book_id", comment.getBook().getId());
        return "commentDelete";
    }

    @PostMapping("/commentDelete")
    public String commentDelete(
            @RequestParam("id") long id
    ) {
        long bookId = commentRepository.findById(id).orElseThrow(NotFoundException::new).getBook().getId();
        commentRepository.deleteById(id);
        return "redirect:/comments?book_id=" + bookId;
    }

    @GetMapping("/commentEdit")
    public String editPage(Model model, @RequestParam("id") Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(NotFoundException::new);
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
        Comment comment = commentRepository.findById(id).orElseThrow(NotFoundException::new);
        comment.setText(text);
        commentRepository.save(comment);
        return "redirect:/comments?book_id=" + comment.getBook().getId();
    }
}
