package ru.otus.spring.exam.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.exam.domain.Question;

@Service
public class QuestionPresenterConsole implements QuestionPresenter {

    private final QuestionConverter questionConverter;
    private final IOService ioService;

    public QuestionPresenterConsole(QuestionConverter questionConverter, IOService ioService){
        this.questionConverter = questionConverter;
        this.ioService = ioService;
    }

    @Override
    public String ask(Question question) {
        ioService.print(this.questionConverter.convertQuestionToString(question));
        return this.ioService.read();
    }


}
