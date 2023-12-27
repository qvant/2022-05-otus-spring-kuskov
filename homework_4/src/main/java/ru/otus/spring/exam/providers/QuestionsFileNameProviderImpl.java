package ru.otus.spring.exam.providers;

public class QuestionsFileNameProviderImpl implements QuestionsFileNameProvider
{

    private final String fileName;
    private final LocaleProvider localeProvider;;

    public QuestionsFileNameProviderImpl(String fileName, LocaleProvider localeProvider) {
        this.fileName = fileName;
        this.localeProvider = localeProvider;
    }

    @Override
    public String getFileName() {
        String language = this.localeProvider.getLocale().getLanguage();
        String localizedFileName = this.fileName.substring(0, this.fileName.length() - 4) + "_" + language + this.fileName.substring(this.fileName.length() - 4);
        return localizedFileName;
    }


}
