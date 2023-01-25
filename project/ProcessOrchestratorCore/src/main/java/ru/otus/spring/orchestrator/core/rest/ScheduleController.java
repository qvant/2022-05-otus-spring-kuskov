package ru.otus.spring.orchestrator.core.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.orchestrator.core.dto.ScheduleDto;
import ru.otus.spring.orchestrator.core.service.ScheduleService;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ScheduleController {
    private final ScheduleService scheduleService;
    @GetMapping("/api/schedules")
    public List<ScheduleDto> getSchedules(){
        return scheduleService.findAll().stream().map(ScheduleDto::toDto).collect(Collectors.toList());
    }

    @GetMapping("/api/schedules/{id}")
    public ScheduleDto getSchedule(@PathVariable("id") Long id){
        return ScheduleDto.toDto(scheduleService.findById(id));
    }

    @PostMapping("/api/schedules/")
    public void createSchedule(@RequestBody ScheduleDto scheduleDto){
        scheduleService.createSchedule(scheduleDto.getName(), scheduleDto.getSeconds());
    }

    @PutMapping({"/api/schedules/", "/api/schedules/{id}"})
    public void editSchedule(@RequestBody ScheduleDto scheduleDto){
        scheduleService.editSchedule(scheduleDto.getId(), scheduleDto.getName(), scheduleDto.getSeconds());
    }

    @DeleteMapping({"/api/schedules/{id}"})
    public void editSchedule(@PathVariable Long id){
        scheduleService.deleteSchedule(id);
    }
}
