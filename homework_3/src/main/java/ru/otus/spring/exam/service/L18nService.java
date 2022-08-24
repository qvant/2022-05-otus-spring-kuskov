package ru.otus.spring.exam.service;

import org.springframework.context.MessageSource;

import java.util.Locale;

public class L18nService {
    private final MessageSource messageSource;
    private final Locale locale;

    public L18nService(MessageSource messageSource, Locale locale) {
        this.messageSource = messageSource;
        this.locale = locale;
    }

    public String getMessage(String messageKey) {
        return this.messageSource.getMessage(messageKey, null, null, this.locale);
    }

}
