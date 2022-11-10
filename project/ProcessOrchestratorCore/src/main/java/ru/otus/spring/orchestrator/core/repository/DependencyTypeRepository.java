package ru.otus.spring.orchestrator.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.orchestrator.core.domain.DependencyType;

public interface DependencyTypeRepository extends JpaRepository<DependencyType, Long> {
}
