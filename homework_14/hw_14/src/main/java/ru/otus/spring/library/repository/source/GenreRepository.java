package ru.otus.spring.library.repository.source;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.library.domain.source.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
