package ru.otus.spring.orchestrator.core.domain;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_SCHEDULES")
    private Long id;
    private String name;
    private Long seconds;

    public Schedule() {}

    public Schedule(Long id, String name, Long seconds) {
        this.id = id;
        this.name = name;
        this.seconds = seconds;
    }

    public Schedule(String name, Long seconds) {
        this.name = name;
        this.seconds = seconds;
    }

    public Instant calcNextDate(Instant previousRun){
        return previousRun.plusSeconds(+ seconds );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSeconds() {
        return seconds;
    }

    public void setSeconds(Long seconds) {
        this.seconds = seconds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
