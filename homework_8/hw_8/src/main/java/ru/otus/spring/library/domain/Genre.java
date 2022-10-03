package ru.otus.spring.library.domain;

import lombok.Data;

@Data
public class Genre {

    private String name;

    public Genre() {

    }


    public Genre(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
