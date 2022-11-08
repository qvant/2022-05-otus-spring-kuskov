package ru.otus.spring.orchestrator.core.dto;

import ru.otus.spring.orchestrator.core.domain.Schedule;
import ru.otus.spring.orchestrator.core.domain.Task;
import ru.otus.spring.orchestrator.core.domain.TaskType;

import java.time.Instant;

public class TaskDto {
    private Long id;
    private String name;
    private String code;
    private Instant nextRun;
    private TaskTypeDto taskType;

    private ScheduleDto schedule;

    public TaskDto(Long id, String name, String code, Instant nextRun, TaskTypeDto taskType, ScheduleDto scheduleDto) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.nextRun = nextRun;
        this.taskType = taskType;
        this.schedule = scheduleDto;
    }

    public static TaskDto toDto(Task task){
        return new TaskDto(task.getId(), task.getName(), task.getTaskCode(), task.getNextRun(), TaskTypeDto.toDto(task.getTaskType()), ScheduleDto.toDto(task.getSchedule()));
    }

    public Task toDomain(){
        return new Task(this.name, this.code, this.nextRun, this.schedule.toDomain(), this.taskType.toDomain());
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

    public ScheduleDto getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleDto schedule) {
        this.schedule = schedule;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
