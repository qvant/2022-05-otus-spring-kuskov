package ru.otus.spring.orchestrator.core.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.otus.spring.orchestrator.core.domain.Task;
import ru.otus.spring.orchestrator.core.domain.TaskInstance;
import ru.otus.spring.orchestrator.core.dto.TaskInstanceDto;

import java.time.Instant;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ListenerService {
    private static final String RESULT_QUEUE_NAME = "process_orchestrator-queue-task-results";
    private final TaskInstanceService taskInstanceService;
    private final DependencyService dependencyService;
    private final TaskService taskService;

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
            List<Task> dependentTasks =  dependencyService.getReadyDependencies(taskInstance.getTask().getId(), taskInstanceDto.getStatus(), taskInstance.getRootTaskInstanceId());
            for (Task task: dependentTasks
                 ) {
                taskService.scheduleRun(task.getId(), taskInstance.getStartedTime(), taskInstance.getRootTaskInstanceId());
            }
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
