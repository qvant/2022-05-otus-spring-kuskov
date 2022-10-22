package ru.otus.spring.library.repository.result;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.library.domain.result.BookTarget;

import java.util.Optional;

public interface BookTargetRepository extends MongoRepository<BookTarget, String> {
    Optional<BookTarget> findByLegacyId(Long id);

}
