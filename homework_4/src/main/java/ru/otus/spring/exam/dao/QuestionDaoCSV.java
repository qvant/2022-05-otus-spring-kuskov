package ru.otus.spring.exam.dao;

import ru.otus.spring.exam.domain.Answer;
import ru.otus.spring.exam.domain.Question;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import ru.otus.spring.exam.providers.QuestionsFileNameProvider;

public class QuestionDaoCSV implements QuestionDao {

    private final QuestionsFileNameProvider questionsFileNameProvider;

    public QuestionDaoCSV(QuestionsFileNameProvider questionsFileNameProvider) {
        this.questionsFileNameProvider = questionsFileNameProvider;
    }

    public List<Question> readAll() {
        ClassLoader classLoader = getClass().getClassLoader();
        String fileName;
        fileName = this.questionsFileNameProvider.getFileName();

        try (
                InputStream inputStream = classLoader.getResourceAsStream(fileName);
                InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                CSVReader reader = new CSVReader(streamReader)
        ) {
            String[] lines;
            List<Question> res = new ArrayList<Question>();
            while (true) {
                try {
                    lines = reader.readNext();
                    if (lines == null) {
                        break;
                    }
                } catch (CsvException err) {
                    System.out.println(err);
                    throw new RuntimeException(err);
                }
                if (lines != null) {
                    List<Answer> answers = new ArrayList<Answer>();
                    Integer correctAnswerIndex = Integer.valueOf(lines[1].trim());
                    for (int i = 0; i < lines.length - 2; i++
                    ) {
                        answers.add(new Answer(lines[i + 2].trim(), (i==correctAnswerIndex)));
                    }
                    String questionText = lines[0].trim();
                    res.add(new Question(questionText, answers, correctAnswerIndex));
                } else {
                    break;
                }
            }
            return res;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
