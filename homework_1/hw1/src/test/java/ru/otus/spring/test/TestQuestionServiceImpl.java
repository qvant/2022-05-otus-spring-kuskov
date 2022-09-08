package ru.otus.spring.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionServiceImpl;
import ru.otus.spring.dao.QuestionDaoCSV;
import ru.otus.spring.dao.QuestionDao;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestQuestionServiceImpl {

    QuestionServiceImpl calculator;

    @BeforeEach
    void setUp() {
        QuestionDao dao = new QuestionDaoCSV("questions_sample.csv");
        calculator = new QuestionServiceImpl(dao);
    }

    @Test
    @DisplayName("Check all questions text")
    void testQuestionsText() {
        Question q = calculator.read();

        assertEquals(q.getText(), "Why do you study here",
                "Question text doesn't match");
        q = calculator.read();
        assertEquals(q.getText(), "How many stars in the sky",
                "Question text doesn't match");
        q = calculator.read();
        assertEquals(q.getText(), "2 * 2 = ",
                "Question text doesn't match");
        q = calculator.read();
        assertEquals(q.getText(), "Test question 1",
                "Question text doesn't match");
        q = calculator.read();
        assertEquals(q.getText(), "Test question 2",
                "Question text doesn't match");
    }

    @Test
    @DisplayName("Check all questions answers")
    void testQuestionsAnswers() {
        Question q = calculator.read();

        String[] answers = q.getAnswers();
        assertEquals(answers.length, 0,
                "Question text doesn't match");
        q = calculator.read();
        answers = q.getAnswers();
        assertEquals(answers.length, 0,
                "Question text doesn't match");
        q = calculator.read();
        answers = q.getAnswers();
        assertEquals(answers.length, 4,
                "Question text doesn't match");
        q = calculator.read();
        answers = q.getAnswers();
        assertEquals(answers.length, 3,
                "Question text doesn't match");
        q = calculator.read();
        answers = q.getAnswers();
        assertEquals(answers.length, 2,
                "Question text doesn't match");
        String[] controlValue = new String[] {"Answer 21", "Answer 22"};
        for (int i = 0; i < q.getAnswers().length; i++)
        {
                assertEquals(answers[i], controlValue[i],
                        "Question text doesn't match");
        }
    }

}