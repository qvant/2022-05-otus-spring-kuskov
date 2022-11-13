package ru.otus.spring.library.domain;

import javax.persistence.*;

@Entity
@Table(name = "authors")
@SequenceGenerator(name = "S_AUTHORS", sequenceName = "S_AUTHORS", initialValue = 999, allocationSize = 1)
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_AUTHORS")
    private Long id;
    private String name;

    public Author() {

    }

    public Author(Long id, String name) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
