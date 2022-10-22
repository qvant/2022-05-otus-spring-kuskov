package ru.otus.spring.library.domain.result;

import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.otus.spring.library.domain.source.Genre;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "books")
@RequiredArgsConstructor
public class BookTarget {
    @Id
    private String id;
    private long legacyId;
    private String title;
    @DBRef(lazy = false)
    private AuthorTarget author;

    private Genre genre;
    private String isbn;

    private List<CommentTarget> comments;


    public BookTarget(String id, String title, AuthorTarget author, Genre genre, String isbn, long legacyId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.legacyId = legacyId;
        this.comments = new ArrayList<CommentTarget>();
    }

    public BookTarget(String title, AuthorTarget author, Genre genre, String isbn, long legacyId) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.legacyId = legacyId;
        this.comments = new ArrayList<CommentTarget>();
    }

    public String getTitle() {
        return title;
    }

    public AuthorTarget getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CommentTarget> getComments() {
        return comments;
    }

    public void setComments(List<CommentTarget> comments) {
        this.comments = comments;
    }
}
