package ru.otus.spring.orchestrator.core.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.otus.spring.orchestrator.core.domain.TaskInstance;
import ru.otus.spring.orchestrator.core.dto.TaskInstanceDto;

import java.time.Instant;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListenerService {
    private static final String RESULT_QUEUE_NAME = "process_orchestrator-queue-task-results";
    private final TaskInstanceService taskInstanceService;

    @RabbitListener(queues = {RESULT_QUEUE_NAME})
    @SneakyThrows
    public void listen(String message) {
        log.warn("Received message " + message);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.findAndRegisterModules();
        TaskInstanceDto taskInstanceDto = objectMapper.readValue(message, TaskInstanceDto.class);
        TaskInstance taskInstance;
        try {
            taskInstance = taskInstanceService.findById(taskInstanceDto.getId());
        }catch (Exception exception){
            log.error(exception.getMessage());
            return;
        }
        taskInstance.setFinishedTime(taskInstanceDto.getFinishedTime());
        taskInstance.setResult(taskInstanceDto.getResult());
        taskInstance.setStatus(taskInstanceDto.getStatus());
        taskInstance.setStartedTime(taskInstanceDto.getStartedTime());
        taskInstanceService.save(taskInstance);

        log.warn("Message " + message + " proceed, response sent");


    }
}
