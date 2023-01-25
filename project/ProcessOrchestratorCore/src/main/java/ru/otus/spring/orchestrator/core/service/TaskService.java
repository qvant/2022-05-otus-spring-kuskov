package ru.otus.spring.orchestrator.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.orchestrator.core.domain.Schedule;
import ru.otus.spring.orchestrator.core.domain.Task;
import ru.otus.spring.orchestrator.core.domain.TaskInstance;
import ru.otus.spring.orchestrator.core.domain.TaskType;
import ru.otus.spring.orchestrator.core.dto.TaskInstanceDto;
import ru.otus.spring.orchestrator.core.repository.TaskInstanceRepository;
import ru.otus.spring.orchestrator.core.repository.TaskRepository;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskInstanceRepository taskInstanceRepository;
    private static final String MAIN_EXCHANGE_NAME = "process_orchestrator-exchange";
    private static final String MAIN_QUEUE_NAME = "process_orchestrator-queue";
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public List<Task> findAll(){
        return taskRepository.findAll();
    }
    public List<Task> findAllOrderedById(){
        Sort sort = Sort.by("id").ascending();
        return taskRepository.findAll(sort);
    }

    public List<Task> findAllOrderedByName(){
        Sort sort = Sort.by("name").ascending();
        return taskRepository.findAll(sort);
    }

    public Task findById(Long id){
        return taskRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Transactional
    public void editTask(Long id, String name, Instant nextRun, Schedule schedule) {
        Task task;
        if (id == null) {
            task = new Task();
        } else {
            task = taskRepository.findById(id).orElseThrow(RuntimeException::new);
        }
        task.setName(name);
        task.setSchedule(schedule);
        task.setNextRun(nextRun);
        taskRepository.save(task);
    }

    @Transactional
    @SneakyThrows
    public void scheduleRun(Long id, Instant runStart, Long rootTaskInstanceId){
        Task task = taskRepository.findById(id).orElseThrow(RuntimeException::new);
        if (task.getSchedule() != null) {
            task.setNextRun(task.getSchedule().calcNextDate(runStart));
        }
        TaskInstance taskInstance = new TaskInstance(task, task.getName(), task.getTaskCode(), task.getTaskType().getId(), runStart, Instant.now());
        taskInstance.setRootTaskInstanceId(rootTaskInstanceId);
        taskInstanceRepository.save(taskInstance);
        taskRepository.save(task);
        val routingKey = "tasks";
        rabbitTemplate.convertAndSend(MAIN_EXCHANGE_NAME, routingKey, objectMapper.writeValueAsString(TaskInstanceDto.toDto(taskInstance)));
        log.warn("Task " + task.getName() + " scheduled");
    }

    @Transactional
    public void createTask(String name, String code, Instant nextRun, Schedule schedule, TaskType taskType){
        Task task = new Task(name, code, nextRun, schedule, taskType);
        taskRepository.save(task);
    }

    @Transactional
    public void editTask(Long id, String name, String code, Instant nextRun, Schedule schedule, TaskType taskType){
        Task task =  taskRepository.findById(id).orElseThrow(RuntimeException::new);
        task.setName(name);
        task.setTaskCode(code);
        task.setNextRun(nextRun);
        task.setSchedule(schedule);
        task.setTaskType(taskType);
        taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
}
