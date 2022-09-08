package ru.otus.spring.library.repository;

import ru.otus.spring.library.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    List<Comment> findAll();

    List<Comment> findByBookId(Long id);

    Optional<Comment> findById(Long id);

    Comment save(Comment comment);

    void deleteById(Long id);
}
