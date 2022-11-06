package ru.otus.spring.orchestrator.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.orchestrator.core.domain.Task;


public interface TaskRepository extends JpaRepository<Task, Long> {
}
