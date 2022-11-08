package ru.otus.spring.orchestrator.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.orchestrator.core.domain.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
