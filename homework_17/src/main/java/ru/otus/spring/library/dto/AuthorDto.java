package ru.otus.spring.library.dto;

import ru.otus.spring.library.domain.Author;

public class AuthorDto {
    private Long id;
    private String name;

    public AuthorDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static AuthorDto toDto(Author author) {
        return new AuthorDto(author.getId(), author.getName());
    }

    public Author toDomain() {
        return new Author(id, name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
