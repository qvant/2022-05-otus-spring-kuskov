package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

public interface QuestionPresenter {
    String ask(Question question);
    void print(String text);
}
