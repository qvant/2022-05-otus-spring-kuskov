package ru.otus.spring.orchestrator.core.dto;

import ru.otus.spring.orchestrator.core.domain.Schedule;

public class ScheduleDto {

    private Long id;
    private String name;
    private Long seconds;

    public ScheduleDto(){}

    public ScheduleDto(Long id, String name, Long seconds) {
        this.id = id;
        this.name = name;
        this.seconds = seconds;
    }

    public static ScheduleDto toDto(Schedule schedule){
        return new ScheduleDto(schedule.getId(), schedule.getName(), schedule.getSeconds());
    }

    public Schedule toDomain(){
        return new Schedule(this.id, this.name, this.seconds);
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

    public Long getSeconds() {
        return seconds;
    }

    public void setSeconds(Long seconds) {
        this.seconds = seconds;
    }
}
