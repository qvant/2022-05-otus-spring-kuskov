package ru.otus.spring.orchestrator.core.dto;

import ru.otus.spring.orchestrator.core.domain.TaskType;

public class TaskTypeDto {
    private Long id;
    private String name;

    public TaskTypeDto(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public static TaskTypeDto toDto(TaskType taskType){
        return new TaskTypeDto(taskType.getId(), taskType.getName());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
