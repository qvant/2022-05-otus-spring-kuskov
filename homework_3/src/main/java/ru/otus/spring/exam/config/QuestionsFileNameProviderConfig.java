package ru.otus.spring.exam.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.exam.providers.LocaleProvider;
import ru.otus.spring.exam.providers.QuestionsFileNameProvider;
import ru.otus.spring.exam.providers.QuestionsFileNameProviderImpl;

import java.util.Locale;

@Configuration
@ConfigurationProperties("application")
public class QuestionsFileNameProviderConfig {
    private String filePath;
    private Locale locale;

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Bean
    public QuestionsFileNameProvider questionsFileNameProvider(LocaleProvider localeProvider) {
        return new QuestionsFileNameProviderImpl(this.filePath, localeProvider.getLocale());
    }
}
