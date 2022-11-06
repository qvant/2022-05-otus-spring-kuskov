package ru.otus.spring.orchestrator.worker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.spring.orchestrator.worker.dto.TaskInstanceDto;

import java.time.Instant;

import static java.lang.Math.min;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskProcessService {

    private static long TASK_TYPE_ORACLE_SQL = 1L;
    private static long TASK_TYPE_ORACLE_STORED_PROCEDURE = 2L;

    private static final Long STATUS_SUCCESS = 1L;

    private final OracleService oracleService;

    public TaskInstanceDto processTask(TaskInstanceDto taskInstanceDto){
        if (taskInstanceDto.getTaskTypeId() == TASK_TYPE_ORACLE_SQL){
            try {
                return oracleService.processSqlTask(taskInstanceDto);
            }
            catch (Exception exception){
                log.error(exception.getMessage());
                taskInstanceDto.setResult(exception.getMessage().substring(0, min(exception.getMessage().length(), 255)));
                taskInstanceDto.setStatus(2L);
                return taskInstanceDto;
            }
        }
        if (taskInstanceDto.getTaskTypeId() == TASK_TYPE_ORACLE_STORED_PROCEDURE){
            try {
                return oracleService.processProcedureTask(taskInstanceDto);
            }
            catch (Exception exception){
                log.error(exception.getMessage());
                taskInstanceDto.setResult(exception.getMessage().substring(0, min(exception.getMessage().length(), 255)));
                taskInstanceDto.setStatus(2L);
                return taskInstanceDto;
            }
        }
        taskInstanceDto.setFinishedTime(Instant.now());
        taskInstanceDto.setStatus(STATUS_SUCCESS);
        taskInstanceDto.setResult("");
        return taskInstanceDto;

    }
}
