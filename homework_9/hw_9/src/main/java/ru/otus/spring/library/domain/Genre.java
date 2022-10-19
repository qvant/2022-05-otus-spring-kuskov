package ru.otus.spring.library.domain;

import javax.persistence.*;

@Entity
@Table(name = "genres")
@SequenceGenerator(name = "S_GENRES", sequenceName = "S_GENRES", initialValue = 999)
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_GENRES")
    private long id;
    private String name;

    public Genre() {

    }

    public Genre(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genre(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
