package ru.otus.spring.exam.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.exam.domain.Question;
import ru.otus.spring.exam.dao.QuestionDao;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    public List<Question> readAll() {
        return this.dao.readAll();
    }

    public boolean checkAnswer(Question question, int answerNumber) {
        return question.getAnswers().get(answerNumber).isCorrect();

    }
}
