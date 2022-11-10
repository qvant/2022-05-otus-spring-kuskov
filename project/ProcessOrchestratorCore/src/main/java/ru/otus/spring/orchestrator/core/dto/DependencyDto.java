package ru.otus.spring.orchestrator.core.dto;

import ru.otus.spring.orchestrator.core.domain.Dependency;
import ru.otus.spring.orchestrator.core.domain.DependencyType;
import ru.otus.spring.orchestrator.core.domain.Task;

public class DependencyDto {

    private Long id;
    private Long type;

    private String typeName;
    private Long taskParentId;
    private String taskParentName;
    private Long taskId;
    private String taskName;

    public DependencyDto(Long id, Long type, Long taskParentId, String taskParentName, Long taskId, String taskName) {
        this.id = id;
        this.type = type;
        this.typeName = DependencyType.getNameById(type);
        this.taskParentId = taskParentId;
        this.taskParentName = taskParentName;
        this.taskId = taskId;
        this.taskName = taskName;
    }

    public static DependencyDto toDto(Dependency dependency){
        return new DependencyDto(dependency.getId(), dependency.getType(), dependency.getTaskParent().getId(), dependency.getTaskParent().getName(),
                dependency.getTask().getId(), dependency.getTask().getName());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
        this.typeName = DependencyType.getNameById(type);
    }

    public Long getTaskParentId() {
        return taskParentId;
    }

    public void setTaskParentId(Long taskParentId) {
        this.taskParentId = taskParentId;
    }

    public String getTaskParentName() {
        return taskParentName;
    }

    public void setTaskParentName(String taskParentName) {
        this.taskParentName = taskParentName;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTypeName() {
        return typeName;
    }

}
