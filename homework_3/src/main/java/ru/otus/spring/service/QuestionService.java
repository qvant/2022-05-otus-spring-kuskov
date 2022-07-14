package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

public interface QuestionService {
    Question[] readAll(String language);
    void ask(Question question);
    Boolean checkAnswer(Question question, String answer);
}
