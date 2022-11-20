package ru.otus.spring.orchestrator.worker.service.postgres;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.orchestrator.worker.config.postgres.PostgresConfiguration;
import ru.otus.spring.orchestrator.worker.dto.TaskInstanceDto;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

//@Service
@Slf4j
@RequiredArgsConstructor
public class PostgresService {
    @Qualifier("postgresEntityManager")
    private final EntityManager entityManager;
//    @Qualifier("postgresEntityManager")
//    private final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean;
    @Transactional(propagation = Propagation.REQUIRED)
    public TaskInstanceDto processSqlTask(TaskInstanceDto taskInstanceDto){
        //jdbc.query(taskInstanceDto.getCode())
        log.warn("Ready to run POSTGRES query " + taskInstanceDto.getCode());
        //EntityManager em = entityManagerFactoryBean.createNativeEntityManager(null);
        Query query = entityManager.createNativeQuery(taskInstanceDto.getCode());
        // TODO: После подключения второй БД начались чудеса с транзакциями, пришлось прейти на ручное управление.
        entityManager.getTransaction().begin();
        try {
            //Query query = em.createNativeQuery(taskInstanceDto.getCode());
            query.executeUpdate();
            entityManager.getTransaction().commit();
        }
        catch (Exception exception){
            log.error(exception.getMessage());
            entityManager.getTransaction().rollback();
            throw exception;
        }
        //var q = query.getFirstResult();
        //System.out.println(q);
        log.warn("Finished " + taskInstanceDto.getCode());
        return taskInstanceDto;
    }

}
