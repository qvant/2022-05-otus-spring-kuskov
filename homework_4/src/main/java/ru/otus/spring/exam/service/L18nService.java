package ru.otus.spring.exam.service;

import org.springframework.context.MessageSource;
import ru.otus.spring.exam.providers.LocaleProvider;

public class L18nService {
    private final MessageSource messageSource;
    private final LocaleProvider localeProvider;

    public L18nService(MessageSource messageSource, LocaleProvider localeProvider) {
        this.messageSource = messageSource;
        this.localeProvider = localeProvider;
    }

    public String getMessage(String messageKey) {
        return this.messageSource.getMessage(messageKey, null, null, this.localeProvider.getLocale());
    }

}
