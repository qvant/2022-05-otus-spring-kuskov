package ru.otus.spring.orchestrator.worker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProcessOrchestratorWorker
{
    public static void main(String[] args) {
        SpringApplication.run(ProcessOrchestratorWorker.class, args);
    }
}
