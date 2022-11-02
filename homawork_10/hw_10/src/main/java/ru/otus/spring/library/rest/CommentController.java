package ru.otus.spring.library.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.spring.library.dto.CommentDto;
import ru.otus.spring.library.exceptions.IncorrectReferenceException;
import ru.otus.spring.library.service.CommentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    @GetMapping("api/comments")
    public List<CommentDto> getBookComments(@RequestParam("book_id") Long bookId) {
        return commentService.findByBookId(bookId).stream().map(CommentDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("api/comment")
    public Optional<CommentDto> getComment(Long id) {
        return commentService.findById(id).map(CommentDto::toDto);
    }

    @PostMapping("api/commentEdit")
    public RedirectView saveComment(CommentDto commentDto) {
        if (commentDto.getId() == null) {
            commentService.addComment(commentDto.getBookId(), commentDto.getText());
        } else {
            commentService.updateComment(commentDto.getId(), commentDto.getText());
        }
        return new RedirectView("/comments?book_id=" + commentDto.getBookId());
    }

    @PostMapping("api/commentDelete")
    public RedirectView deleteComment(Long id) {
        long bookId = commentService.findById(id).orElseThrow(IncorrectReferenceException::new).getBook().getId();
        commentService.deleteComment(id);
        return new RedirectView("/comments?book_id=" + bookId);
    }
}
