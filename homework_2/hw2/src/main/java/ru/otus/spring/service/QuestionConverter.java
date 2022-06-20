package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

public class QuestionConverter {
    public String convertQuestionToString(Question question){
        return question.getText() + "? " + String.join(", ", question.getAnswers());
    }
}
