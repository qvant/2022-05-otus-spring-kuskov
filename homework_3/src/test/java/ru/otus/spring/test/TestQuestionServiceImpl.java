package ru.otus.spring.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.QuestionServiceImpl;
import ru.otus.spring.dao.QuestionDaoCSV;
import ru.otus.spring.dao.QuestionDao;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TestQuestionServiceImpl {

    private QuestionServiceImpl questionService;

    private final static String EMPTY_LANGUAGE = "empty_lang";

    @BeforeEach
    void setUp() {
        QuestionDao dao = new QuestionDaoCSV("questions_sample_test.csv", Arrays.asList(new String[]{"en", "ru"}));
        this.questionService = new QuestionServiceImpl(dao);
    }

    @Test
    @DisplayName("Check all questions read")
    void testAllQuestionsRead() {
        Question[] q = questionService.readAll(EMPTY_LANGUAGE);

        assertEquals(5, q.length,
                "Not all questions read");
    }

    @Test
    @DisplayName("Check question text")
    void testQuestionsText() {
        Question[] q = questionService.readAll(EMPTY_LANGUAGE);

        assertEquals("1 + 1", q[0].getText(),
                "Question text doesn't match");
    }

    @Test
    @DisplayName("Check questions answers")
    void testQuestionsAnswers() {
        Question[] q = questionService.readAll(EMPTY_LANGUAGE);

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