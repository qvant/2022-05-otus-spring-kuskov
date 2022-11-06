package ru.otus.spring.orchestrator.core.dto;

import ru.otus.spring.orchestrator.core.domain.Task;

import java.time.Instant;

public class TaskDto {
    private String name;
    private String code;
    private Instant nextRun;
    private TaskTypeDto taskType;

    public TaskDto(String name, String code, Instant nextRun, TaskTypeDto taskType) {
        this.name = name;
        this.code = code;
        this.nextRun = nextRun;
        this.taskType = taskType;
    }

    public static TaskDto toDto(Task task){
        return new TaskDto((task.getName()), task.getTaskCode(), task.getNextRun(), TaskTypeDto.toDto(task.getTaskType()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Instant getNextRun() {
        return nextRun;
    }

    public void setNextRun(Instant nextRun) {
        this.nextRun = nextRun;
    }

    public TaskTypeDto getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskTypeDto taskType) {
        this.taskType = taskType;
    }
}
