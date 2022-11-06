package ru.otus.spring.orchestrator.worker.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.orchestrator.worker.dto.TaskInstanceDto;

import java.time.Instant;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListenerService {
    private static final String MAIN_EXCHANGE_NAME = "process_orchestrator-exchange";
    private static final String MAIN_QUEUE_NAME = "process_orchestrator-queue-tasks";
    private static final Long STATUS_SUCCESS = 1L;
    private final RabbitTemplate rabbitTemplate;
    //private final ObjectMapper objectMapper;

    //@Scheduled(initialDelay = 3000, fixedDelay = 3000)
    @RabbitListener(queues = {MAIN_QUEUE_NAME})
    @SneakyThrows
    public void listen(String message) {
        log.warn("Received message " + message);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.findAndRegisterModules();
        TaskInstanceDto taskInstance = objectMapper.readValue(message, TaskInstanceDto.class);
        taskInstance.setStartedTime(Instant.now());
        // TODO: do something
        val routingKey = "task-results";
        taskInstance.setFinishedTime(Instant.now());
        taskInstance.setStatus(STATUS_SUCCESS);
        taskInstance.setResult("");
        rabbitTemplate.convertAndSend(MAIN_EXCHANGE_NAME, routingKey, objectMapper.writeValueAsString(taskInstance));
        log.warn("Message " + message + " proceed, response sent");


    }
}
