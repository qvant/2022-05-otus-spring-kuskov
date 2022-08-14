package ru.otus.spring.exam.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class L18nService {
    private final MessageSource messageSource;
    private final Locale locale;

    public L18nService(MessageSource messageSource) {
        this.messageSource = messageSource;
        this.locale = Locale.getDefault();
    }

    public String getMessage(String messageKey) {
        return this.messageSource.getMessage(messageKey, null, null, this.locale);
    }

}
