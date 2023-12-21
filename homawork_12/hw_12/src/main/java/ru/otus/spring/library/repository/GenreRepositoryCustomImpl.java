package ru.otus.spring.library.repository;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.library.exceptions.HasDependentObjectsException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

@RequiredArgsConstructor
public class GenreRepositoryCustomImpl implements GenreRepositoryCustom {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public void deleteByIdWithDependencyException(Long id) throws HasDependentObjectsException {
        Query query = em.createQuery("delete Genre g where id = :id");
        query.setParameter("id", id);
        try {
            query.executeUpdate();
        } catch (PersistenceException exception) {
            throw new HasDependentObjectsException(exception.getMessage());
        }
    }
}
