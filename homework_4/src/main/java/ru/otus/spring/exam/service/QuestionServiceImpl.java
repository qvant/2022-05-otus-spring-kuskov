package ru.otus.spring.exam.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.exam.domain.Question;
import ru.otus.spring.exam.dao.QuestionDao;

@Service
public class QuestionServiceImpl implements QuestionService{
    private final QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao)
    {
        this.dao = dao;
    }

    public  Question[] readAll(String language) {
        return this.dao.readAll(language);
    }

    public void ask(Question question) {

    }

    public Boolean checkAnswer(Question question, String answer) {
        return question.getAnswers()[question.getCorrectAnswerIndex()].equals(answer);
    }
}
