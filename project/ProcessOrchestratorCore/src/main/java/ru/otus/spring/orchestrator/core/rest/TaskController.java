package ru.otus.spring.orchestrator.core.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.orchestrator.core.dto.TaskDto;
import ru.otus.spring.orchestrator.core.service.TaskService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TaskController {
    private final TaskService taskService;
    @GetMapping("/api/tasks")
    public List<TaskDto> getTasks(@RequestParam(name = "sort") Optional<Long> sorted){
        if (sorted.equals(1L)) {
            return taskService.findAllOrderedById().stream().map(TaskDto::toDto).collect(Collectors.toList());
        }
        if (sorted.equals(2L)) {
            return taskService.findAllOrderedByName().stream().map(TaskDto::toDto).collect(Collectors.toList());
        }
        return taskService.findAll().stream().map(TaskDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/api/tasks/{id}")
    public TaskDto getTask(@PathVariable("id") Long id){
        return TaskDto.toDto(taskService.findById(id));
    }

    @PostMapping("/api/tasks/")
    public void createTask(@RequestBody TaskDto taskDto){
        taskService.createTask(taskDto.getName(), taskDto.getCode(), taskDto.getNextRun(), taskDto.getSchedule().toDomain(), taskDto.getTaskType().toDomain());
    }

    @PutMapping({"/api/tasks/", "/api/tasks/{id}"})
    public void editTask(@RequestBody TaskDto taskDto){
        taskService.editTask(taskDto.getId(), taskDto.getName(), taskDto.getCode(), taskDto.getNextRun(), taskDto.getSchedule().toDomain(), taskDto.getTaskType().toDomain());
    }

    @DeleteMapping({"/api/tasks/{id}"})
    public void editTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }
}
