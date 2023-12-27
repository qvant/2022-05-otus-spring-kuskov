package ru.otus.spring.exam.domain;

import java.util.List;

public class Question {

    private final String text;
    private final List<Answer> answers;

    public Question(String text, List<Answer> answers) {
        this.text = text;
        this.answers = answers;
    }

    public String getText() {
        return this.text;
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }


}
