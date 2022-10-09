package ru.otus.spring.library.repository;

import ru.otus.spring.library.exceptions.HasDependentObjectsException;

public interface AuthorRepositoryCustom {

    void deleteByIdWithDependencyException(Long id) throws HasDependentObjectsException;
}
