package ru.otus.spring.orchestrator.core.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.orchestrator.core.dto.TaskDto;
import ru.otus.spring.orchestrator.core.dto.TaskInstanceDto;
import ru.otus.spring.orchestrator.core.dto.TaskTypeDto;
import ru.otus.spring.orchestrator.core.service.TaskInstanceService;
import ru.otus.spring.orchestrator.core.service.TaskService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TaskInstanceController {
    private final TaskInstanceService taskService;
    @GetMapping("/api/task_instances")
    public List<TaskInstanceDto> getTasks(){
        return taskService.findAll().stream().map(TaskInstanceDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/api/task_instance/{id}")
    public TaskInstanceDto getTask(@RequestParam("id") Long id){
        return TaskInstanceDto.toDto(taskService.findById(id));
    }
}
