package ru.otus.spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.library.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>, GenreRepositoryCustom {

}
