package ru.otus.spring.service;

import ru.otus.spring.domain.Question;
import ru.otus.spring.dao.QuestionDao;

public class QuestionServiceImpl implements QuestionService{
    private final QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao)
    {
        this.dao = dao;
    }

    public  Question read() {
        return this.dao.read();
    }
}
