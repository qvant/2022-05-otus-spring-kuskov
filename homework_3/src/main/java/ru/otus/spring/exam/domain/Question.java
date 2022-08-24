package ru.otus.spring.exam.domain;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private final String text;
    private final List<Answer> answers;

    private final Integer correctAnswerIndex;

    public Question(String text, List<Answer> answers, Integer correctAnswerIndex) {
        this.text = text;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public Question(String text) {
        this.text = text;
        this.answers = new ArrayList<Answer>();
        this.correctAnswerIndex = -1;
    }

    public String getText() {
        return this.text;
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }

    public Integer getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }


}
