package ru.otus.spring.library.repository.result;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.library.domain.result.AuthorTarget;

public interface AuthorTargetRepository extends MongoRepository<AuthorTarget, String> {


}
