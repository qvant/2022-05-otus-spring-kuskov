package ru.otus.spring.exam.providers;

import java.util.Locale;

public class QuestionsFileNameProviderImpl implements QuestionsFileNameProvider
{

    private final String fileName;
    private final Locale locale;

    @Override
    public String getFileName() {
        String language = this.locale.getLanguage();
        String localizedFileName = this.fileName.substring(0, this.fileName.length() - 4) + "_" + language + this.fileName.substring(this.fileName.length() - 4);
        return localizedFileName;
    }

    public QuestionsFileNameProviderImpl(String fileName, Locale locale) {
        this.fileName = fileName;
        this.locale = locale;
    }

}
