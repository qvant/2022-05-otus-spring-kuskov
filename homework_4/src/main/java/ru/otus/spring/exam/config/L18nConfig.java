package ru.otus.spring.exam.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.exam.providers.LocaleProvider;
import ru.otus.spring.exam.service.L18nService;

@Configuration
public class L18nConfig {

    @Bean
    public L18nService l18nService(MessageSource messageSource, LocaleProvider localeProvider) {
        return new L18nService(messageSource, localeProvider);
    }
}
