package ru.otus.spring.exam.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.exam.domain.Question;

@Service
public class ExamServiceImpl implements ExamService {
    private final QuestionService questionService;
    private final L18nService l18nService;
    private final QuestionPresenter questionPresenter;
    private final Integer passagePercent;
    private final Integer questionNumber;

    public void run(String firstName, String lastName) {

        Question[] questions = questionService.readAll(l18nService.getLocaleName());
        int correctAnswers = 0;
        for (int i = 0; i < questionNumber; i++) {
            String answer = questionPresenter.ask(questions[i]);
            if (questionService.checkAnswer(questions[i], answer)) {
                correctAnswers++;
            }
        }
        int correctAnswersPercent = 0;
        if (questionNumber != 0) {
            correctAnswersPercent = correctAnswers * 100 / questionNumber;
        }
        if (correctAnswersPercent >= passagePercent) {
            questionPresenter.printWithParameters(l18nService.getMessage("results.succeed"),
                    firstName, lastName, correctAnswers, questionNumber);
        } else {
            questionPresenter.printWithParameters(l18nService.getMessage("results.failed"),
                    firstName, lastName, correctAnswers, questionNumber, passagePercent);
        }
    }

    public ExamServiceImpl(QuestionService questionService, QuestionPresenter questionPresenter,
                           @Value("#{T(java.lang.Integer).parseInt(${exam.passagePercent})}") int passagePercent,
                           @Value("#{T(java.lang.Integer).parseInt(${exam.questionNumber})}") int questionNumber) {
        this.questionService = questionService;
        this.questionPresenter = questionPresenter;
        this.passagePercent = passagePercent;
        this.questionNumber = questionNumber;
        this.l18nService = new L18nService();
    }
}

