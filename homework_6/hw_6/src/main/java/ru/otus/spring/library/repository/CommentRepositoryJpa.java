package ru.otus.spring.library.repository;

import org.springframework.stereotype.Repository;
import ru.otus.spring.library.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public CommentRepositoryJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Comment> findAll() {
        return entityManager.createQuery("select c from Comment c join fetch c.book", Comment.class).getResultList();
    }

    @Override
    public List<Comment> findByBookId(Long id) {
        TypedQuery<Comment> query = entityManager.createQuery("select c from Comment c join fetch c.book where c.book_id = :id", Comment.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Comment.class, id));
    }

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() <= 0) {
            entityManager.persist(comment);
            return comment;
        }
        return entityManager.merge(comment);

    }

    @Override
    public void deleteById(Long id) {
        Query query = entityManager.createQuery("delete Comment c where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
