package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Question;

@Service
public class ExamServiceImpl implements ExamService {
    private final QuestionService questionService;
    private final QuestionPresenter questionPresenter;
    private final Integer passagePercent;
    private final Integer questionNumber;

    public void run() {

        Question[] questions = questionService.readAll();
        int correctAnswers = 0;
        String firstName = questionPresenter.ask(new Question("What is your name?"));
        String lastName = questionPresenter.ask(new Question("What is your surname?"));
        for (int i = 0; i < questionNumber; i++
        ) {
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
            questionPresenter.print(firstName + " " + lastName + " you passed with " + correctAnswers +
                    " correct answers from " + questionNumber + " questions");
        } else {
            questionPresenter.print(firstName + " " + lastName + " failed with " + correctAnswers +
                    " correct answers from " + questionNumber + " questions, " + passagePercent + " percent needed");
        }
    }

    public ExamServiceImpl(QuestionService questionService, QuestionPresenter questionPresenter,
                           @Value("#{T(java.lang.Integer).parseInt(${exam.passagePercent})}") int passagePercent,
                           @Value("#{T(java.lang.Integer).parseInt(${exam.questionNumber})}") int questionNumber) {
        this.questionService = questionService;
        this.questionPresenter = questionPresenter;
        this.passagePercent = passagePercent;
        this.questionNumber = questionNumber;
    }
}

