package ru.otus.spring.exam.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.exam.domain.Answer;
import ru.otus.spring.exam.domain.Question;
import ru.otus.spring.exam.providers.QuestionsFileNameProvider;
import ru.otus.spring.exam.service.IOService;
import ru.otus.spring.exam.service.QuestionServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TestQuestionServiceImpl {

    private QuestionServiceImpl questionService;
    @MockBean
    IOService ioService;
    @MockBean
    QuestionsFileNameProvider questionsFileNameProvider;
    @Autowired
    ApplicationContext context;

    @BeforeEach
    void setUp() {
        Mockito.when(ioService.read()).thenReturn("A");
        Mockito.when(questionsFileNameProvider.getFileName()).thenReturn("questions_sample_test.csv");
        questionService = context.getBean(QuestionServiceImpl.class);
    }

    @Test
    @DisplayName("Check all questions read")
    void testAllQuestionsRead() {
        List<Question> q = questionService.readAll();

        assertEquals(5, q.size(),
                "Not all questions read");
    }

    @Test
    @DisplayName("Check question text")
    void testQuestionsText() {
        List<Question> q = questionService.readAll();

        assertEquals("1 + 1", q.get(0).getText(),
                "Question text doesn't match");
    }

    @Test
    @DisplayName("Check questions answers")
    void testQuestionsAnswers() {
        List<Question> q = questionService.readAll();

        List<Answer> answers = q.get(0).getAnswers();
        assertEquals(3, answers.size(),
                "Question text doesn't match");
        String[] controlValue = new String[]{"1", "2", "4"};
        for (int i = 0; i < q.get(0).getAnswers().size(); i++) {
            assertEquals(answers.get(i).getText(), controlValue[i],
                    "Question text doesn't match");
        }
    }

}