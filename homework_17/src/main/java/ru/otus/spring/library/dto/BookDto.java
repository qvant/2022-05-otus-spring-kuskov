package ru.otus.spring.library.dto;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.library.domain.Book;

@RequiredArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private String isbn;
    private Long genreId;
    private Long authorId;

    private String authorName;
    private String genreName;

    public BookDto(Long id, String title, String isbn, Long genreId, Long authorId, String genreName, String authorName) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.genreId = genreId;
        this.genreName = genreName;
        this.authorId = authorId;
        this.authorName = authorName;
    }

    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getIsbn(), book.getGenre().getId(), book.getAuthor().getId(), book.getGenre().getName(), book.getAuthor().getName());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
