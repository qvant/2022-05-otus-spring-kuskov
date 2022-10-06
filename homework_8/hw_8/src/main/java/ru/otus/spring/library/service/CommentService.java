package ru.otus.spring.library.service;

public interface CommentService {

    void showBookComments(String bookId);

    void addComment(String bookId, String text);

    void updateComment(String bookId, int commentId, String text);

    void deleteComment(String bookId, int commentId);
}
