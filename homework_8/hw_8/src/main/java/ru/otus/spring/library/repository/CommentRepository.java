package ru.otus.spring.library.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.library.domain.Comment;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    @EntityGraph(attributePaths = {"book"})
    List<Comment> findByBookId(Long id);

}
