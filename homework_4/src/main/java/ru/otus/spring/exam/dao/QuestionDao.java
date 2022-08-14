package ru.otus.spring.exam.dao;

import ru.otus.spring.exam.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> readAll();
}
