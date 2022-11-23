package ru.otus.spring.orchestrator.core.domain;

import javax.persistence.*;
import java.time.Instant;


@Entity
@Table(name = "tasks_instances")
public class TaskInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_TASKS_INSTANCES")
    private Long id;
    //TODO: Must be lazu
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    private Task task;
    // Fields from Task, because Task, despite doesn't having history, is mutable. TaskInstance, by definition, are not.
    private String name;
    private String code;
    @Column(name = "task_type_id")
    private Long taskTypeId;
    @Column(name = "scheduled_time")
    private Instant scheduledTime;
    @Column(name = "started_time")
    private Instant startedTime;
    @Column(name = "queued_time")
    private Instant queuedTime;
    @Column(name = "finished_time")
    private Instant finishedTime;

    private Long status;
    private String result;

    @Column(name = "root_task_instance_id")
    private Long rootTaskInstanceId;


    public TaskInstance() {
    }

    public TaskInstance(Long id, Task task, String name, String code, Long taskTypeId, Instant scheduledTime, Instant startedTime, Instant queuedTime, Instant finishedTime){
        this.id = id;
        this.task = task;
        this.name = name;
        this.code = code;
        this.taskTypeId = taskTypeId;
        this.scheduledTime = scheduledTime;
        this.startedTime = startedTime;

        this.queuedTime = queuedTime;
        this.finishedTime = finishedTime;
    }

    public TaskInstance(Task task, String name, String code, Long taskTypeId, Instant scheduledTime, Instant queuedTime){
        this.task = task;
        this.taskTypeId = task.getTaskType().getId();
        this.name = name;
        this.code = code;
        this.taskTypeId = taskTypeId;
        this.scheduledTime = scheduledTime;
        this.queuedTime = queuedTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
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

    public Long getTaskTypeId() {
        return taskTypeId;
    }

    public void setTaskTypeId(Long taskTypeId) {
        this.taskTypeId = taskTypeId;
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
        if (status == null){
            return 0L;
        }
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getRootTaskInstanceId() {
        return rootTaskInstanceId;
    }

    public void setRootTaskInstanceId(Long rootTaskInstanceId) {
        this.rootTaskInstanceId = rootTaskInstanceId;
    }
}
