package ru.otus.spring.orchestrator.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.orchestrator.core.domain.TaskType;

public interface TaskTypeRepository extends JpaRepository<TaskType, Long> {
}
