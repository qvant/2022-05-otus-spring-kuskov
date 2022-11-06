package ru.otus.spring.orchestrator.worker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.orchestrator.worker.dto.TaskInstanceDto;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

@Service
@Slf4j
@RequiredArgsConstructor
public class OracleService {
    private final NamedParameterJdbcOperations jdbc;
    private final EntityManager entityManager;
    @Transactional
    public TaskInstanceDto processSqlTask(TaskInstanceDto taskInstanceDto){
        //jdbc.query(taskInstanceDto.getCode())
        log.warn("Ready to run query " + taskInstanceDto.getCode());
        Query query = entityManager.createNativeQuery(taskInstanceDto.getCode());
        query.executeUpdate();
        log.warn("Finished " + taskInstanceDto.getCode());
        return taskInstanceDto;
    }

    @Transactional
    public TaskInstanceDto processProcedureTask(TaskInstanceDto taskInstanceDto){
        //jdbc.query(taskInstanceDto.getCode())
        log.warn("Ready to execute procedure " + taskInstanceDto.getCode());
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery(taskInstanceDto.getCode());
        storedProcedure.execute();
        log.warn("Finished " + taskInstanceDto.getCode());
        return taskInstanceDto;
    }
}
