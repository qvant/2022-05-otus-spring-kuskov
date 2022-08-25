package ru.otus.spring.exam.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.exam.domain.Answer;
import ru.otus.spring.exam.domain.Question;
import ru.otus.spring.exam.providers.QuestionsFileNameProviderImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TestQuestionServiceImpl {

    @Autowired
    private QuestionServiceImpl questionService;
    @MockBean
    private IOService ioService;
    @MockBean
    private QuestionsFileNameProviderImpl questionsFileNameProviderImpl;

    @BeforeEach
    void setUp() {
        Mockito.when(ioService.read()).thenReturn("A");
        Mockito.when(questionsFileNameProviderImpl.getFileName()).thenReturn("questions_sample_test.csv");
    }

    @Test
    @DisplayName("Check all questions read")
    void testAllQuestionsRead() {
        List<Question> q = questionService.readAll();

        assertEquals(5, q.size(), "Not all questions read");
    }

    @Test
    @DisplayName("Check question text")
    void testQuestionsText() {
        List<Question> q = questionService.readAll();

        assertEquals("1 + 1", q.get(0).getText(), "Question text doesn't match");
    }

    @Test
    @DisplayName("Check questions answers")
    void testQuestionsAnswers() {
        List<Question> q = questionService.readAll();

        List<Answer> answers = q.get(0).getAnswers();
        assertEquals(3, answers.size(), "Question text doesn't match");
        String[] controlValue = new String[]{"1", "2", "4"};
        for (int i = 0; i < q.get(0).getAnswers().size(); i++) {
            assertEquals(answers.get(i).getText(), controlValue[i], "Question text doesn't match");
        }
    }

}