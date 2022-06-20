package ru.otus.spring.config;

import org.springframework.context.annotation.Configuration;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.service.QuestionService;
import ru.otus.spring.service.QuestionServiceImpl;

@Configuration
public class ServicesConfig {
    public QuestionService questionService(QuestionDao dao){
        return new QuestionServiceImpl(dao);
    }
}
