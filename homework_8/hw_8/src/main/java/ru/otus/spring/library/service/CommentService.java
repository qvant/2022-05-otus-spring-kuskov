package ru.otus.spring.library.service;

import org.bson.types.ObjectId;

public interface CommentService {

    void showBookComments(ObjectId bookId);

    void addComment(ObjectId bookId, String text);

    void updateComment(ObjectId bookId, int commentId, String text);

    void deleteComment(ObjectId bookId, int commentId);
}
