package ru.otus.spring.exam.service;

import ru.otus.spring.exam.domain.Question;

public interface QuestionPresenter {
    String ask(Question question);
    void print(String text);
    void printWithParameters(String text, Object obj_1, Object obj_2, Object obj_3, Object obj_4, Object obj_5);
    void printWithParameters(String text, Object obj_1, Object obj_2, Object obj_3, Object obj_4);
}
