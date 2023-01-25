package ru.otus.spring.orchestrator.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.orchestrator.core.domain.Task;
import ru.otus.spring.orchestrator.core.domain.TaskInstance;
import ru.otus.spring.orchestrator.core.repository.TaskInstanceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskInstanceService {
    private final TaskInstanceRepository taskInstanceRepository;
    public List<TaskInstance> findAll(){
        return taskInstanceRepository.findAll();
    }

    public TaskInstance findById(Long id){
        return taskInstanceRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Transactional
    public void save(TaskInstance taskInstance){
        taskInstanceRepository.save(taskInstance);
    }
}
