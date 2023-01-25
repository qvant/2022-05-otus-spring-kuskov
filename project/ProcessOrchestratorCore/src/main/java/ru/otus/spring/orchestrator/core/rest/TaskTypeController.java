package ru.otus.spring.orchestrator.core.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.orchestrator.core.dto.TaskTypeDto;
import ru.otus.spring.orchestrator.core.service.TaskTypeService;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TaskTypeController {
    private final TaskTypeService taskTypeService;

    @GetMapping("/api/taskTypes")
    public List<TaskTypeDto> getTasks() {
        return taskTypeService.findAll().stream().map(TaskTypeDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/api/taskTypes/{id}")
    public TaskTypeDto getTask(@PathVariable("id") Long id) {
        return TaskTypeDto.toDto(taskTypeService.findById(id));
    }
}
