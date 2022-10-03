package ru.otus.spring.library.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.library.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {

}
