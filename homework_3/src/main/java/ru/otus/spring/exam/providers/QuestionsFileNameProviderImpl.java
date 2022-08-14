package ru.otus.spring.exam.providers;

import java.util.List;
import java.util.Locale;

public class QuestionsFileNameProviderImpl implements QuestionsFileNameProvider {

    private final String fileName;
    private final List<String> supportedLanguages;

    @Override
    public String getFileName() {
        String localizedFileName = this.fileName;
        String language = Locale.getDefault().getLanguage();
        for (String supportedLanguage : this.supportedLanguages
        ) {
            if (language.equals(supportedLanguage)) {
                localizedFileName = fileName.substring(0, fileName.length() - 4) + "_" + language +
                        fileName.substring(fileName.length() - 4);
                break;
            }
        }
        return localizedFileName;
    }

    public QuestionsFileNameProviderImpl(String fileName, List<String> supportedLanguages) {
        this.fileName = fileName;
        this.supportedLanguages = supportedLanguages;
    }

}
