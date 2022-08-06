package ru.otus.spring.exam.dao;

import ru.otus.spring.exam.domain.Question;

public interface QuestionDao {
    Question[] readAll(String language);
}
