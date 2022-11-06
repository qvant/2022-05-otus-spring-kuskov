package ru.otus.spring.library.dto;

import ru.otus.spring.library.domain.Genre;

public class GenreDto {
    private Long id;
    private String name;

    public GenreDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static GenreDto toDto(Genre genre) {
        return new GenreDto(genre.getId(), genre.getName());
    }

    public Genre toDomain() {
        return new Genre(id, name);
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
