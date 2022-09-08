package ru.otus.spring.library.repository;

import org.springframework.stereotype.Repository;
import ru.otus.spring.library.domain.Author;
import ru.otus.spring.library.exceptions.HasDependentObjectsException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryJpa implements AuthorRepository {
    @PersistenceContext
    private EntityManager em;

    public AuthorRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Author> findAll() {
        return em.createQuery("select a from Author a", Author.class).getResultList();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public Author save(Author author) {
        if (author.getId() <= 0) {
            em.persist(author);
            return author;
        }
        return em.merge(author);
    }

    @Override
    public void deleteById(Long id) throws HasDependentObjectsException {
        Query query = em.createQuery("delete Author a where id = :id");
        query.setParameter("id", id);
        try {
            query.executeUpdate();
        } catch (javax.persistence.PersistenceException exception) {
            throw new HasDependentObjectsException("Нельзя удалить автора с id " + id + ", есть зависимости");
        }
    }
}
