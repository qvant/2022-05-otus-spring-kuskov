package ru.otus.spring.service;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class L18nService {
    private ReloadableResourceBundleMessageSource messageSource;
    private final Locale locale;

    public L18nService(){
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        this.messageSource = messageSource;
        this.messageSource.setBasename("classpath:messages");
        this.messageSource.setDefaultEncoding("UTF-8");
        this.locale = Locale.getDefault();
    }

    public String getMessage(String messageKey){
        return this.messageSource.getMessage(messageKey, null, null, this.locale);
    }

    public String getLocaleName(){
        return this.locale.getLanguage();
    }
}
