package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Question;

import java.io.*;

@Service
public class QuestionPresenterConsole implements QuestionPresenter{

    private final QuestionConverter questionConverter;
    @Override
    public String ask(Question question) {
        String answer;
        System.out.println(this.questionConverter.convertQuestionToString(question));
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(System.in));
        try {
            answer = bufferedReader.readLine();
        } catch (Exception e) {
            answer = "";
        }
        return answer;
    }

    @Override
    public void print(String text) {
        System.out.println(text);
    }

    public QuestionPresenterConsole (){
        this.questionConverter = new QuestionConverter();
    }




}
