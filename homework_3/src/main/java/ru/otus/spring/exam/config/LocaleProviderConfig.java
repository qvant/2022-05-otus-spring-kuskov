package ru.otus.spring.exam.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.exam.providers.LocaleProvider;
import ru.otus.spring.exam.providers.LocaleProviderImpl;

import java.util.Locale;

@Configuration
@ConfigurationProperties("l18n")
public class LocaleProviderConfig {

    private Locale locale;


    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Bean
    public LocaleProvider localeProvider() {
        return new LocaleProviderImpl(this.locale);
    }
}
