package ru.otus.spring.library.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("api/books/{id}/comments/")
    public List<CommentDto> getBookComments(@PathVariable("id") Long bookId) {
        return commentService.findByBookId(bookId).stream().map(CommentDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("api/comments/{id}")
    public Optional<CommentDto> getComment(@PathVariable("id") Long id) {
        return commentService.findById(id).map(CommentDto::toDto);
    }

    @PostMapping("api/comments")
    public void saveComment(@RequestBody CommentDto commentDto) {
        commentService.addComment(commentDto.getBookId(), commentDto.getText());
    }

    @PutMapping({"api/comments", "api/comments/{id}"})
    public void editComment(@RequestBody CommentDto commentDto) {
        commentService.updateComment(commentDto.getId(), commentDto.getText());
    }

    @DeleteMapping("api/comments/{id}")
    public void deleteComment(@PathVariable Long id) {
        long bookId = commentService.findById(id).orElseThrow(IncorrectReferenceException::new).getBook().getId();
        commentService.deleteComment(id);
    }
}
