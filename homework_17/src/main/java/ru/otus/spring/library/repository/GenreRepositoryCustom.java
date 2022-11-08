package ru.otus.spring.library.repository;

import ru.otus.spring.library.exceptions.HasDependentObjectsException;

public interface GenreRepositoryCustom {
    void deleteByIdWithDependencyException(Long id) throws HasDependentObjectsException;
}
