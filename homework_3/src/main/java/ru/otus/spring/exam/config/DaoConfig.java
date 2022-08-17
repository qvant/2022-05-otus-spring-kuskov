package ru.otus.spring.exam.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.exam.dao.QuestionDao;
import ru.otus.spring.exam.dao.QuestionDaoCSV;
import ru.otus.spring.exam.providers.QuestionsFileNameProvider;
import ru.otus.spring.exam.providers.QuestionsFileNameProviderImpl;

import java.util.Locale;

@Configuration
@ConfigurationProperties("application")
public class DaoConfig {
    private String filePath;
    private Locale locale;


    @Bean
    public QuestionDao questionDao(L18nConfig l18nConfig) {
        QuestionsFileNameProvider questionsFileNameProvider = new QuestionsFileNameProviderImpl(this.filePath, this.locale);
        return new QuestionDaoCSV(questionsFileNameProvider);
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
