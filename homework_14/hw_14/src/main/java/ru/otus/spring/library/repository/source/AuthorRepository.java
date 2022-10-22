package ru.otus.spring.library.repository.source;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.library.domain.source.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
