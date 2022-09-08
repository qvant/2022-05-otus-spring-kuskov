package ru.otus.spring.library.service;

public interface CommentService {
    void showComments();

    void showBookComments(Long bookId);

    void addComment(Long bookId, String text);

    void updateComment(long id, String text);

    void deleteComment(long id);
}
