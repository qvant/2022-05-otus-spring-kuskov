package ru.otus.spring.exam.service;

import ru.otus.spring.exam.domain.Question;

public interface QuestionPresenter {
    String ask(Question question);
}
