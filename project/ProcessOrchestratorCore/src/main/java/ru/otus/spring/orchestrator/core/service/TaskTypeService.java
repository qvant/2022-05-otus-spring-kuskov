package ru.otus.spring.orchestrator.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.orchestrator.core.domain.Task;
import ru.otus.spring.orchestrator.core.domain.TaskType;
import ru.otus.spring.orchestrator.core.repository.TaskTypeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskTypeService {

    private final TaskTypeRepository taskTypeRepository;
    public List<TaskType> findAll(){
        return taskTypeRepository.findAll();
    }

    public TaskType findById(Long id){
        return taskTypeRepository.findById(id).orElseThrow(RuntimeException::new);
    }

}
