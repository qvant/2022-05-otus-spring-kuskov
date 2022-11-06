package ru.otus.spring.orchestrator.worker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.orchestrator.worker.domain.Task;


public interface TaskRepository extends JpaRepository<Task, Long> {
}
