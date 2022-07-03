package ru.otus.spring.config;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.service.QuestionService;
import ru.otus.spring.service.QuestionServiceImpl;

@Service
public class ServicesConfig {
    public QuestionService questionService(QuestionDao dao){
        return new QuestionServiceImpl(dao);
    }
}
