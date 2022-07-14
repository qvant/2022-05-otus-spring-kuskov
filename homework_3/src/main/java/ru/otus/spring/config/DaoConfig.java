package ru.otus.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.dao.QuestionDaoCSV;

import java.util.List;

@Component
@ConfigurationProperties("application")
public class DaoConfig {
    private String filePath;

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    private class File {
        private String path;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }

    private class L18n {
        private List<String> languages;

        public List<String> getLanguages() {
            return languages;
        }

        public void setLanguages(List<String> languages) {
            this.languages = languages;
        }
    }

    private List<String> languages;

    @Bean
    public QuestionDao questionDao() {
        return new QuestionDaoCSV(this.filePath, this.languages);
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
