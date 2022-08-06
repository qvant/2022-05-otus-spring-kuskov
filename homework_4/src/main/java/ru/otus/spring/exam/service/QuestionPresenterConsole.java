package ru.otus.spring.exam.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.exam.domain.Question;

import java.io.*;

@Service
public class QuestionPresenterConsole implements QuestionPresenter{

    private final PrintStream out;

    public QuestionPresenterConsole() throws java.io.UnsupportedEncodingException {
        out = new PrintStream(System.out, true, "UTF-8");
    }

    private String convertQuestionToString(Question question){
        return question.getText() + "? " + String.join(", ", question.getAnswers());
    }

    @Override
    public String ask(Question question) {
        String answer;
        out.println(this.convertQuestionToString(question));
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
        out.println(text);
    }

    @Override
    public void printWithParameters(String text, Object obj_1, Object obj_2, Object obj_3, Object obj_4, Object obj_5) {
        out.printf(text, obj_1, obj_2, obj_3, obj_4, obj_5);
        out.println();
    }

    @Override
    public void printWithParameters(String text, Object obj_1, Object obj_2, Object obj_3, Object obj_4) {
        out.printf(text, obj_1, obj_2, obj_3, obj_4);
        out.println();
    }


}
