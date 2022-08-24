package ru.otus.spring.exam.service;

import ru.otus.spring.exam.domain.Question;

import java.util.List;

public interface QuestionService {
    List<Question> readAll();

    boolean checkAnswer(Question question, int answerNumber);
}
