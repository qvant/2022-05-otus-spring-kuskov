package ru.otus.spring.library.service;

import ru.otus.spring.library.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {


    void addComment(Long bookId, String text);

    void updateComment(long id, String text);

    void deleteComment(long id);

    List<Comment> findByBookId(Long id);

    Optional<Comment> findById(long id);
}
