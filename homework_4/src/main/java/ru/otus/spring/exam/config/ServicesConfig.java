package ru.otus.spring.exam.config;

import org.springframework.stereotype.Service;
import ru.otus.spring.exam.dao.QuestionDao;
import ru.otus.spring.exam.service.QuestionService;
import ru.otus.spring.exam.service.QuestionServiceImpl;

@Service
public class ServicesConfig {
    public QuestionService questionService(QuestionDao dao){
        return new QuestionServiceImpl(dao);
    }
}
