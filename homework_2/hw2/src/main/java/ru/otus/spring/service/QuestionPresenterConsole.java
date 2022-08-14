package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Question;

import java.io.*;

@Service
public class QuestionPresenterConsole implements QuestionPresenter{

    private String convertQuestionToString(Question question){
        return question.getText() + "? " + String.join(", ", question.getAnswers());
    }

    @Override
    public String ask(Question question) {
        String answer;
        System.out.println(this.convertQuestionToString(question));
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




}
