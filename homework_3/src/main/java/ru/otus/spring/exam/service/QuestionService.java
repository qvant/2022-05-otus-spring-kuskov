package ru.otus.spring.exam.service;

import ru.otus.spring.exam.domain.Question;

import java.util.List;

public interface QuestionService {
    List<Question> readAll();

    void ask(Question question);

    Boolean checkAnswer(Question question, Integer answerNumber);
}
