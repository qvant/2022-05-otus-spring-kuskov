package ru.otus.spring.orchestrator.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.orchestrator.core.domain.Schedule;
import ru.otus.spring.orchestrator.core.domain.Task;
import ru.otus.spring.orchestrator.core.repository.TaskRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public Task findById(Long id){
        return taskRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Transactional
    public void editTask(Long id, String name, Instant nextRun, Schedule schedule) {
        Task task;
        if (id == null) {
            task = new Task();
        } else {
            task = taskRepository.findById(id).orElseThrow(RuntimeException::new);
        }
        task.setName(name);
        task.setSchedule(schedule);
        task.setNextRun(nextRun);
        taskRepository.save(task);
    }

    public void scheduleRun(Instant runStart){

    }
}
