package ru.otus.spring.library.domain;

public class Book {
    private long id;
    private final String title;
    private final Author author;
    private final Genre genre;
    private final String isbn;

    public Book(long id, String title, Author author, Genre genre, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
    }

    public Book(String title, Author author, Genre genre, String isbn) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
    }

    ;

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
