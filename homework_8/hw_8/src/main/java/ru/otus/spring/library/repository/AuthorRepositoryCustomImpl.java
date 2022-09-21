package ru.otus.spring.library.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.library.exceptions.HasDependentObjectsException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryCustomImpl implements AuthorRepositoryCustom {


    @PersistenceContext
    private final EntityManager em;


    @Override
    public void deleteByIdWithDependencyException(Long id) throws HasDependentObjectsException {

        Query query = em.createQuery("delete Author a where id = :id");
        query.setParameter("id", id);
        try {
            query.executeUpdate();
        } catch (PersistenceException exception) {
            throw new HasDependentObjectsException(exception.getMessage());
        }

    }
}
