package ru.otus.spring.exam.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.otus.spring.exam.domain.Question;
import ru.otus.spring.exam.domain.Student;
import ru.otus.spring.exam.domain.TestResult;

import java.util.List;

@Component
public class ExamService implements CommandLineRunner {
    private final QuestionService questionService;
    private final L18nService l18nService;
    private final QuestionPresenter questionPresenter;
    private final IOService ioService;
    private final int passagePercent;
    private final int questionNumber;

    public ExamService(QuestionService questionService, QuestionPresenter questionPresenter,
                       @Value("${exam.passagePercent}") int passagePercent,
                       @Value("${exam.questionNumber}") int questionNumber,
                       L18nService l18nService,
                       IOService ioService) {
        this.questionService = questionService;
        this.questionPresenter = questionPresenter;
        this.passagePercent = passagePercent;
        this.questionNumber = questionNumber;
        this.l18nService = l18nService;
        this.ioService = ioService;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Question> questions = questionService.readAll();
        Student student = this.createStudent();
        TestResult testResult = this.askQuestions(questions);
        this.showResults(testResult, student);
    }

    private Student createStudent() {
        ioService.print(l18nService.getMessage("questions.first_name"));
        String firstName = ioService.read();
        ioService.print(l18nService.getMessage("questions.last_name"));
        String lastName = ioService.read();
        return new Student(lastName, firstName);
    }

    private TestResult askQuestions(List<Question> questions) {
        TestResult testResult = new TestResult(passagePercent);
        for (int i = 0; i < questionNumber; i++) {
            String answer = questionPresenter.ask(questions.get(i));
            try {
                if (questionService.checkAnswer(questions.get(i), Integer.parseInt(answer))) {
                    testResult.addCorrectAnswer();
                } else {
                    testResult.addIncorrectAnswer();
                }
            } catch (Exception exception){
                this.ioService.print(l18nService.getMessage("errors.wrong_code"));
                testResult.addIncorrectAnswer();
            }
        }
        return testResult;
    }

    private void showResults(TestResult testResult, Student student) {
        if (testResult.isPassed()) {
            ioService.printWithParameters(l18nService.getMessage("results.succeed"),
                    student.getFirstName(), student.getLastName(), testResult.getCorrectAnswers(), questionNumber);
        } else {
            ioService.printWithParameters(l18nService.getMessage("results.failed"),
                    student.getFirstName(), student.getLastName(), testResult.getCorrectAnswers(), questionNumber, passagePercent);
        }
    }

}

