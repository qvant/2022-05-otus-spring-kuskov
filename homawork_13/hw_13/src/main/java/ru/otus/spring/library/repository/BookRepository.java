package ru.otus.spring.library.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PostFilter;
import ru.otus.spring.library.domain.Book;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {
    @EntityGraph(attributePaths = {"genre", "author"})
    @PostFilter("hasPermission(filterObject, 'READ')")
    List<Book> findAll();

}
