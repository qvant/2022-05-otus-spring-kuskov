package ru.otus.spring.orchestrator.core.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.orchestrator.core.dto.TaskDto;
import ru.otus.spring.orchestrator.core.service.TaskService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TaskController {
    private final TaskService taskService;
    @GetMapping("/api/tasks")
    public List<TaskDto> getTasks(){
        return taskService.findAll().stream().map(TaskDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/api/task/{id}")
    public TaskDto getTask(@RequestParam("id") Long id){
        return TaskDto.toDto(taskService.findById(id));
    }
}
