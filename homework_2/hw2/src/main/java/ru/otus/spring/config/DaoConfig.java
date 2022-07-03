package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.dao.QuestionDaoCSV;

import java.io.IOException;

@Configuration
@PropertySource("classpath:application.properties")
public class DaoConfig {
    @Value("${file.path}")
    private String filePath;

    @Bean
    public QuestionDao questionDao(){
        try {
            return new QuestionDaoCSV(this.filePath);
        } catch (IOException e)
        {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
}
