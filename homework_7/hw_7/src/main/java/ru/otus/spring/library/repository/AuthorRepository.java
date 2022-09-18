package ru.otus.spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.library.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>, AuthorRepositoryCustom {

}
