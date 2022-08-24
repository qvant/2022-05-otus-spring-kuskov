package ru.otus.spring.exam.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.exam.domain.Answer;
import ru.otus.spring.exam.domain.Question;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionConverterImpl implements QuestionConverter {
    @Override
    public String convertQuestionToString(Question question) {
        List<String> answersText = new ArrayList<String>();
        for (Answer answer : question.getAnswers()
        ) {
            answersText.add(answer.getText());
        }
        return question.getText() + "? " + String.join(", ", answersText);
    }
}
