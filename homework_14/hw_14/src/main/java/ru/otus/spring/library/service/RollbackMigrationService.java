package ru.otus.spring.library.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class RollbackMigrationService {
    private final MongoDatabaseFactory mongoDatabaseFactory;

    public void rollbackAuthors() {
        mongoDatabaseFactory.getMongoDatabase().getCollection("authors").drop();
        log.info("Authors removed from mongodb");
    }

    public void rollbackBooks() {
        mongoDatabaseFactory.getMongoDatabase().getCollection("books").drop();
        log.info("Books removed from mongodb");
    }
}
