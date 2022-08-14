package ru.otus.spring.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionServiceImpl;
import ru.otus.spring.dao.QuestionDaoCSV;
import ru.otus.spring.dao.QuestionDao;

import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TestQuestionServiceImpl {

    private QuestionServiceImpl questionService;

    @BeforeEach
    void setUp() throws IOException {
        QuestionDao dao = new QuestionDaoCSV("questions_sample_test.csv");
        this.questionService = new QuestionServiceImpl(dao);
    }

    @Test
    @DisplayName("Check all questions read")
    void testAllQuestionsRead() {
        Question[] q = questionService.readAll();

        assertEquals(5, q.length,
                "Not all questions read");
    }

    @Test
    @DisplayName("Check question text")
    void testQuestionsText() {
        Question[] q = questionService.readAll();

        assertEquals("1 + 1", q[0].getText(),
                "Question text doesn't match");
    }

    @Test
    @DisplayName("Check questions answers")
    void testQuestionsAnswers() {
        Question[] q = questionService.readAll();

        String[] answers = q[0].getAnswers();
        assertEquals(3, answers.length,
                "Question text doesn't match");
        String[] controlValue = new String[] {"1", "2", "4"};
        for (int i = 0; i < q[0].getAnswers().length; i++)
        {
            assertEquals(answers[i], controlValue[i],
                    "Question text doesn't match");
        }
    }

}