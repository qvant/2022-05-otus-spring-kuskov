package ru.otus.spring.library.domain;

import javax.persistence.*;

@Entity
@Table(name = "genres")
@SequenceGenerator(name = "S_GENRES", sequenceName = "S_GENRES", initialValue = 999, allocationSize = 1)
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_GENRES")
    private Long id;
    private String name;

    public Genre() {

    }

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genre(String name) {
        this.name = name;
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
