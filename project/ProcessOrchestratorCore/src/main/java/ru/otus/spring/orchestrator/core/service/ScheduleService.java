package ru.otus.spring.orchestrator.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.orchestrator.core.domain.Schedule;
import ru.otus.spring.orchestrator.core.repository.ScheduleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {
    private final ScheduleRepository repository;

    public List<Schedule> findAll(){
        return repository.findAll();
    }

    public Schedule findById(Long id){
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Transactional
    public void createSchedule(String name, Long seconds){
        Schedule schedule = new Schedule(name, seconds);
        repository.save(schedule);
    }

    @Transactional
    public void editSchedule(Long id, String name, Long seconds){
        Schedule schedule = repository.findById(id).orElseThrow(RuntimeException::new);
        schedule.setName(name);
        schedule.setSeconds(seconds);
        repository.save(schedule);
    }

    @Transactional
    public void deleteSchedule(Long id){
        repository.deleteById(id);
    }

}
