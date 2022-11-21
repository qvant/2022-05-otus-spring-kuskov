package ru.otus.spring.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostFilter;
import ru.otus.spring.library.domain.Author;
import ru.otus.spring.library.domain.Genre;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long>, GenreRepositoryCustom {
    @PostFilter("hasPermission(filterObject, 'READ')")
    List<Genre> findAll();
}
