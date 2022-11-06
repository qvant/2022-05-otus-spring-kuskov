package ru.otus.spring.orchestrator.core.dto;

import ru.otus.spring.orchestrator.core.domain.TaskInstance;

import java.time.Instant;

public class TaskInstanceDto {
    private Long id;
    private TaskDto task;
    private String name;
    private String code;
    private Long taskTypeId;
    private Instant scheduledTime;
    private Instant startedTime;
    private Instant queuedTime;
    private Instant finishedTime;

    private Long Status;
    private String Result;


    public TaskInstanceDto(Long id, TaskDto task, String name, String code, Long taskTypeId, Instant scheduledTime, Instant startedTime, Instant queuedTime, Instant finishedTime, Long status, String result) {
        this.id = id;
        this.task = task;
        this.name = name;
        this.code = code;
        this.taskTypeId = taskTypeId;
        this.scheduledTime = scheduledTime;
        this.startedTime = startedTime;
        this.queuedTime = queuedTime;
        this.finishedTime = finishedTime;
        Status = status;
        Result = result;
    }

    public static TaskInstanceDto toDto(TaskInstance taskInstance) {
        return new TaskInstanceDto(taskInstance.getId(), TaskDto.toDto(taskInstance.getTask()), taskInstance.getName(), taskInstance.getCode(), taskInstance.getTaskTypeId(), taskInstance.getScheduledTime(), taskInstance.getStartedTime(), taskInstance.getQueuedTime(), taskInstance.getFinishedTime(), taskInstance.getStatus(), taskInstance.getResult());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskDto getTask() {
        return task;
    }

    public void setTask(TaskDto task) {
        this.task = task;
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

    public Instant getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Instant scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public Instant getStartedTime() {
        return startedTime;
    }

    public void setStartedTime(Instant startedTime) {
        this.startedTime = startedTime;
    }

    public Instant getQueuedTime() {
        return queuedTime;
    }

    public void setQueuedTime(Instant queuedTime) {
        this.queuedTime = queuedTime;
    }

    public Instant getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(Instant finishedTime) {
        this.finishedTime = finishedTime;
    }

    public Long getStatus() {
        return Status;
    }

    public void setStatus(Long status) {
        Status = status;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public Long getTaskTypeId() {
        return taskTypeId;
    }

    public void setTaskTypeId(Long taskTypeId) {
        this.taskTypeId = taskTypeId;
    }
}
