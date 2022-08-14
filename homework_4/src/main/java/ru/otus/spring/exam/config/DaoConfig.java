package ru.otus.spring.exam.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.exam.dao.QuestionDao;
import ru.otus.spring.exam.dao.QuestionDaoCSV;
import ru.otus.spring.exam.providers.QuestionsFileNameProvider;
import ru.otus.spring.exam.providers.QuestionsFileNameProviderImpl;

import java.util.List;

@Configuration
@ConfigurationProperties("application")
public class DaoConfig {
    private String filePath;

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    private List<String> languages;

    @Bean
    public QuestionDao questionDao() {
        QuestionsFileNameProvider questionsFileNameProvider = new QuestionsFileNameProviderImpl(this.filePath, this.languages);
        return new QuestionDaoCSV(questionsFileNameProvider);
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
