package ru.otus.spring.orchestrator.worker.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ListenerService {
    //@Scheduled(initialDelay = 3000, fixedDelay = 3000)
    @RabbitListener(queues = {"process_orchestrator-queue-tasks"})
    public void listen(String message){
        log.warn("Received message " + message);
    }
}
