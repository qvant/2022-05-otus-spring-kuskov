package ru.otus.spring.library.domain;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@SequenceGenerator(name = "S_COMMENTS", sequenceName = "S_COMMENTS")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_COMMENTS")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    private String text;


    public Comment() {

    }

    public Comment(Book book, String text) {
        this.book = book;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Book getBook() {
        return book;
    }

}
