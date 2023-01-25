package ru.otus.spring.orchestrator.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProcessOrchestratorCore {
    public static void main(String[] args) {
        SpringApplication.run(ProcessOrchestratorCore.class, args);
    }
}
