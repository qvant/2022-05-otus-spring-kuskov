package ru.otus.spring.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.spring.domain.Question;
import ru.otus.spring.service.*;
import ru.otus.spring.dao.QuestionDaoCSV;
import ru.otus.spring.dao.QuestionDao;

import org.mockito.junit.jupiter.MockitoExtension;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)

public class TestExamServiceImpl {
    private ExamServiceImpl examService;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUp() throws IOException {
        QuestionDao dao = new QuestionDaoCSV("questions_sample.csv");
        QuestionService questionService = new QuestionServiceImpl(dao);
        QuestionPresenter questionPresenter = new QuestionPresenterConsole();
        this.examService = new ExamServiceImpl(questionService, questionPresenter, 50, 5);
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    @DisplayName("Check all questions read")
    void testAllQuestionsRead() {
        String userInput = "Andrew" + System.getProperty("line.separator") + "AAA"+ System.lineSeparator()  + "K.\n2\n4\n0\n20\n8\n";
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
        examService.run();
        assertEquals("What is your name?? \n" +
                "What is your surname?? \n" +
                "1 + 1? 1, 2, 4\n" +
                "2 * 2? 4, 3, 5\n" +
                "2 * 0? 0, 2, 4\n" +
                "2 * 10? 20, 2, 4\n" +
                "2 ^ 4? 8, 2, 16\n" +
                "Andrew K. failed with 0 correct answers from 5 questions, 1111 percent needed", outContent.toString());
    }
    @AfterEach
    void cleanUp(){
        System.setOut(originalOut);
        System.setIn(originalIn);
        System.setErr(originalErr);
    }
}
