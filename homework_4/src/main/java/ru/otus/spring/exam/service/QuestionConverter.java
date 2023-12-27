package ru.otus.spring.exam.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.exam.domain.Question;

@Service
public interface QuestionConverter {
    String convertQuestionToString(Question question);
}
