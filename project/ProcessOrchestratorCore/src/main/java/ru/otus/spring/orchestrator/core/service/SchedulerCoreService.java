package ru.otus.spring.orchestrator.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.otus.spring.orchestrator.core.domain.Task;
import ru.otus.spring.orchestrator.core.repository.TaskRepository;

import java.time.Instant;



@Service
@Slf4j
@RequiredArgsConstructor
public class SchedulerCoreService {
    private static final String MAIN_EXCHANGE_NAME = "process_orchestrator-exchange";
    private static final String MAIN_QUEUE_NAME = "process_orchestrator-queue";
    private final TaskRepository taskRepository;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final TaskService taskService;
    @Scheduled(initialDelay = 1000, fixedRate = 3000)
    public void run() {
        try {


        Instant runStart = Instant.now();
        var tasks = taskRepository.findByNextRunIsNotNull();
        for (Task task: tasks
             ) {
            if ( task.getNextRun().isBefore(runStart))
            {
                taskService.scheduleRun(task.getId(), runStart, null);
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
