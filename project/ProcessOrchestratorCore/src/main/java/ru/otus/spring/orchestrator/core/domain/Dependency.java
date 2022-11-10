package ru.otus.spring.orchestrator.core.domain;

import javax.persistence.*;

@Entity
@Table(name="dependencies")
public class Dependency {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dependency_gen")
    @SequenceGenerator(name = "dependency_gen", sequenceName = "S_DEPENDENCIES")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "dependency_type_id", nullable = false)
    private Long type;
    //TODO: must be eager
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_parent_id")
    private Task taskParent;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    private Task task;

    public Dependency(){};
    public Dependency(Long type, Task taskParent, Task task) {
        this.type = type;
        this.taskParent = taskParent;
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Task getTaskParent() {
        return taskParent;
    }

    public void setTaskParent(Task taskParent) {
        this.taskParent = taskParent;
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
    }

}
