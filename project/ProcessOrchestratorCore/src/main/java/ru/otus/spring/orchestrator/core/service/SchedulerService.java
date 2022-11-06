package ru.otus.spring.orchestrator.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.orchestrator.core.dto.TaskDto;
import ru.otus.spring.orchestrator.core.domain.Task;
import ru.otus.spring.orchestrator.core.repository.TaskRepository;

import java.time.Instant;



@Service
@Slf4j
@RequiredArgsConstructor
public class SchedulerService {
    private static final String MAIN_EXCHANGE_NAME = "process_orchestrator-exchange";
    private static final String MAIN_QUEUE_NAME = "process_orchestrator-queue";
    private final TaskRepository taskRepository;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    @Scheduled(initialDelay = 1000, fixedRate = 3000)
    // rework!!!! has to be separate service
    @Transactional
    //@Bean
    public void run() {
        log.info("Scheduled run");
        System.out.println("AAAA");
        try {


        Instant runStart = Instant.now();
        var tasks = taskRepository.findAll();
        System.out.println(tasks.size());
        for (Task task: tasks
             ) {
            if (task.getNextRun().isBefore(runStart))
            {
                task.setNextRun(task.getSchedule().calcNextDate(runStart));
                taskRepository.save(task);
                val routingKey = "tasks";
                rabbitTemplate.convertAndSend(MAIN_EXCHANGE_NAME, routingKey, objectMapper.writeValueAsString(TaskDto.toDto(task)));
                log.warn("Task " + task.getName() + " scheduled");
            }
            else
            {
                log.warn("Task " + task.getName() + " skipped");
            }
        }
        }
        catch (Exception e){
            System.out.println(e);
            ;
        }
    }
}
