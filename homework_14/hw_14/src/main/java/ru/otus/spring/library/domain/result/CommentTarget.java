package ru.otus.spring.library.domain.result;

import lombok.Data;

@Data
public class CommentTarget {
    private String text;
    private Long legacyId;


    public CommentTarget() {

    }

    public CommentTarget(
            String text, Long legacyId) {
        this.text = text;
        this.legacyId = legacyId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getLegacyId() {
        return legacyId;
    }

    public void setLegacyId(Long legacyId) {
        this.legacyId = legacyId;
    }
}
