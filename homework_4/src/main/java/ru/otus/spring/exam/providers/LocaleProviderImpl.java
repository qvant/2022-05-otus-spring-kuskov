package ru.otus.spring.exam.providers;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@ConfigurationProperties("l18n")
public class LocaleProviderImpl implements LocaleProvider {

    private Locale locale;

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public Locale getLocale() {
        return this.locale;
    }

}
