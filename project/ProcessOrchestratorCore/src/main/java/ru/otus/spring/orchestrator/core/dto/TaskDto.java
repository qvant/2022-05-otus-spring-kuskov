package ru.otus.spring.orchestrator.core.dto;

import ru.otus.spring.orchestrator.core.domain.Task;

import java.time.Instant;

public class TaskDto {
    private String name;
    private String code;
    private Instant nextRun;

    public TaskDto(String name, String code, Instant nextRun) {
        this.name = name;
        this.code = code;
        this.nextRun = nextRun;
    }

    public static TaskDto toDto(Task task){
        return new TaskDto((task.getName()), task.getTaskCode(), task.getNextRun());
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
}
