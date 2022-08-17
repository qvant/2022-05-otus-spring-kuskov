package ru.otus.spring.exam.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.exam.service.L18nService;

import java.util.Locale;

@Configuration
@ConfigurationProperties("application")
public class L18nConfig {
    private Locale locale;

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
    @Bean
    public L18nService l18nService(MessageSource messageSource){
        return new L18nService(messageSource, this.locale);
    }
}
