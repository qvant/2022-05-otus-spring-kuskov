package ru.otus.spring.library.domain.result;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "authors")
public class AuthorTarget {
    @Id
    private String id;
    private long legacyId;
    private String name;

    public AuthorTarget() {

    }

    public AuthorTarget(String id, String name, long legacyId) {
        this.id = id;
        this.name = name;
        this.legacyId = legacyId;
    }

    public AuthorTarget(String name, long legacyId) {
        this.name = name;
        this.legacyId = legacyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getLegacyId() {
        return legacyId;
    }
}
