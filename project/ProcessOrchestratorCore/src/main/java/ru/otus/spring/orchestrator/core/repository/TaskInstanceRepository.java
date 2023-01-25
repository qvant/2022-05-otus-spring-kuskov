package ru.otus.spring.orchestrator.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.orchestrator.core.domain.TaskInstance;

import java.util.List;

public interface TaskInstanceRepository extends JpaRepository <TaskInstance, Long> {
    List<TaskInstance> findByRootTaskInstanceId(Long rootTaskInstanceId);
}
