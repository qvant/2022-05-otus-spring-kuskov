package ru.otus.spring.exam.service;

import ru.otus.spring.exam.domain.Question;

public interface QuestionService {
    Question[] readAll(String language);
    void ask(Question question);
    Boolean checkAnswer(Question question, String answer);
}
