package ru.otus.spring.orchestrator.core.domain;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_TASKS")
    private Long id;
    private String name;
    private String code;
    @Column(name = "next_run")
    private Instant nextRun;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void execute() {

    }


    public void setSchedule() {

    }


    public void setTaskCode(String code) {
        this.code = code;
    }


    public String getTaskCode() {
        return code;
    }


    public Instant getNextRun() {
        return nextRun;
    }

    public void setNextRun(Instant nextRun) {
        this.nextRun = nextRun;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
