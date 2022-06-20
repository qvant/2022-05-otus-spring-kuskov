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

public class QuestionDaoCSV implements QuestionDao{

    private final CSVReader reader;

    public QuestionDaoCSV(String fileName) throws java.io.IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        this.reader = new CSVReader(streamReader);
    }
    public Question[] readAll() {
        String[] lines;
        List<Question> res = new ArrayList<Question>() ;
        while (true) {
            try {
                lines = this.reader.readNext();
            } catch (IOException | CsvException err) {
                lines = null;
            }
            if (lines != null) {
                String[] answers = new String[lines.length - 2];
                for (int i=0; i < lines.length-2;i++
                     ) {
                    answers[i] = lines[i+2].trim();
                }
                res.add(new Question(lines[0].trim(), answers, Integer.valueOf(lines[1].trim())));
            }
            else {
                break;
            }
        }
        return res.toArray(new Question[0]);
    }
}
