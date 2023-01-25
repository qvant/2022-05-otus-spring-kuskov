package ru.otus.spring.orchestrator.worker.dto;

import java.time.Instant;

public class TaskInstanceDto {
    private Long id;

    private String name;
    private String code;
    private Long taskTypeId;
    private Instant scheduledTime;
    private Instant startedTime;
    private Instant queuedTime;
    private Instant finishedTime;
    private Long Status;
    private String Result;

    public TaskInstanceDto(){
        ;
    }

    public TaskInstanceDto(Long id, String name, String code, Long taskTypeId, Instant scheduledTime, Instant startedTime, Instant queuedTime, Instant finishedTime, Long status) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.taskTypeId = taskTypeId;
        this.scheduledTime = scheduledTime;
        this.startedTime = startedTime;
        this.queuedTime = queuedTime;
        this.finishedTime = finishedTime;
        Status = status;
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
        this.Status = status;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        this.Result = result;
    }

    public Long getTaskTypeId() {
        return taskTypeId;
    }

    public void setTaskTypeId(Long taskTypeId) {
        this.taskTypeId = taskTypeId;
    }
}
