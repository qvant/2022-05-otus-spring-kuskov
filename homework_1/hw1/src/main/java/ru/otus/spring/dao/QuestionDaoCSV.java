package ru.otus.spring.dao;

import ru.otus.spring.domain.Question;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class QuestionDaoCSV implements QuestionDao{

    private final CSVReader reader;

    public QuestionDaoCSV(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        InputStreamReader streamReader =
                new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        this.reader = new CSVReader(streamReader);
    }
    public Question read() {

        String line;
        String[] lines;
        try {
            lines = this.reader.readNext();
        } catch (IOException | CsvException err) {
            lines = null;
        }
        if (lines != null) {
            return new Question(lines[0], Arrays.copyOfRange(lines, 1, lines.length));
        }
        return null;
    }
}
