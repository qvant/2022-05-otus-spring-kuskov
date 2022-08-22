package ru.otus.spring.exam.providers;

import java.util.Locale;

public class LocaleProviderImpl implements LocaleProvider {

    private Locale locale;

    public LocaleProviderImpl(Locale locale) {
        this.locale = locale;
    }

    @Override
    public Locale getLocale() {
        return this.locale;
    }

}
