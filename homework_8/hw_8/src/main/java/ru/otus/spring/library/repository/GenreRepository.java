package ru.otus.spring.library.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.library.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, ObjectId> {

}
