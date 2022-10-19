package ru.otus.spring.library.domain;

import javax.persistence.*;

@Entity
@Table(name = "authors")
@SequenceGenerator(name = "S_AUTHORS", sequenceName = "S_AUTHORS", initialValue = 999)
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_AUTHORS")
    private long id;
    private String name;

    public Author() {

    }

    public Author(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Author(String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
