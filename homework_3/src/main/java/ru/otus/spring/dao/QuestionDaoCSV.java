package ru.otus.spring.dao;

import ru.otus.spring.domain.Question;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class QuestionDaoCSV implements QuestionDao {

    private final String fileName;
    private final List<String> supportedLanguages;

    public QuestionDaoCSV(String fileName, List<String> supportedLanguages) {
        this.fileName = fileName;
        this.supportedLanguages = supportedLanguages;
    }

    public Question[] readAll(String language) {
        ClassLoader classLoader = getClass().getClassLoader();
        String localizedfileName;
        localizedfileName = this.fileName;
        for (String sypportedLanguage : this.supportedLanguages
        ) {
            if (language.equals(sypportedLanguage)) {
                localizedfileName = fileName.substring(0, fileName.length() - 4) + "_" + language +
                        fileName.substring(fileName.length() - 4);
                break;
            }
        }

        try (
                InputStream inputStream = classLoader.getResourceAsStream(localizedfileName);
                InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                CSVReader reader = new CSVReader(streamReader);
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
                    String[] answers = new String[lines.length - 2];
                    for (int i = 0; i < lines.length - 2; i++
                    ) {
                        answers[i] = lines[i + 2].trim();
                    }
                    String questionText = lines[0].trim();
                    Integer correctAnswerIndex = Integer.valueOf(lines[1].trim());
                    res.add(new Question(questionText, answers, correctAnswerIndex));
                } else {
                    break;
                }
            }
            return res.toArray(new Question[0]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
