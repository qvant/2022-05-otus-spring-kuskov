package ru.otus.spring.exam.providers;

import java.util.Locale;

public class QuestionsFileNameProviderImpl implements QuestionsFileNameProvider {

    private final String fileName;
    private final Locale locale;

    @Override
    public String getFileName() {
        String localizedFileName = this.fileName;
        String language = this.locale.getLanguage();
        localizedFileName = fileName.substring(0, fileName.length() - 4) + "_" + language + fileName.substring(fileName.length() - 4);
        return localizedFileName;
    }

    public QuestionsFileNameProviderImpl(String fileName, Locale locale) {
        this.fileName = fileName;
        this.locale = locale;
    }

}
