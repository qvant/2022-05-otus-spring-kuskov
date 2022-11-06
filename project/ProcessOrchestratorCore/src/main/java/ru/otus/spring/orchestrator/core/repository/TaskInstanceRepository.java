package ru.otus.spring.orchestrator.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.orchestrator.core.domain.TaskInstance;

public interface TaskInstanceRepository extends JpaRepository <TaskInstance, Long> {
}
