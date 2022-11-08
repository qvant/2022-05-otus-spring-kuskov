package ru.otus.spring.library.dto;

import ru.otus.spring.library.domain.Comment;

public class CommentDto {
    private Long id;
    private Long bookId;
    private String text;

    public CommentDto(Long id, Long bookId, String text) {
        this.id = id;
        this.bookId = bookId;
        this.text = text;
    }

    public static CommentDto toDto(Comment comment) {
        return new CommentDto(comment.getId(), comment.getBook().getId(), comment.getText());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
