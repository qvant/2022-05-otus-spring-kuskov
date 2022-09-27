package ru.otus.spring.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import ru.otus.spring.library.listners.AuthorCascadeSaveMongoEvent;
import ru.otus.spring.library.listners.GenreCascadeSaveMongoEvent;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "library";
    }

    @Bean
    public AuthorCascadeSaveMongoEvent authorCascadeSaveMongoEvent() {
        return new AuthorCascadeSaveMongoEvent();
    }

    @Bean
    public GenreCascadeSaveMongoEvent genreCascadeSaveMongoEvent() {
        return new GenreCascadeSaveMongoEvent();
    }

}
