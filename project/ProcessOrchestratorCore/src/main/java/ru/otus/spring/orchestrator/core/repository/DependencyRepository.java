package ru.otus.spring.orchestrator.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.spring.orchestrator.core.domain.Dependency;

import java.util.List;

public interface DependencyRepository extends JpaRepository<Dependency, Long> {
    @Query("select d from Dependency d where d.taskParent.id = ?1")
    List<Dependency> findByTaskParent(Long taskParent);
    List<Dependency> findByTaskAndType(Long task, Long type);
}
