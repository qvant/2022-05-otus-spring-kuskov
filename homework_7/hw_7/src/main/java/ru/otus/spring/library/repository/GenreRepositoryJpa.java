package ru.otus.spring.library.repository;

import org.springframework.stereotype.Repository;
import ru.otus.spring.library.domain.Genre;
import ru.otus.spring.library.exceptions.HasDependentObjectsException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepositoryJpa implements GenreRepository {
    @PersistenceContext
    private final EntityManager em;

    public GenreRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Genre> findAll() {
        return em.createQuery("select g from Genre g", Genre.class).getResultList();
    }

    @Override
    public Optional<Genre> findById(Long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public Genre save(Genre genre) {
        if (genre.getId() <= 0) {
            em.persist(genre);
            return genre;
        }
        return em.merge(genre);

    }

    @Override
    public void deleteById(Long id) throws HasDependentObjectsException {
        Query query = em.createQuery("delete Genre g where id = :id");
        query.setParameter("id", id);
        try {
            query.executeUpdate();
        } catch (PersistenceException exception) {
            throw new HasDependentObjectsException(exception.getMessage());
        }
    }
}
