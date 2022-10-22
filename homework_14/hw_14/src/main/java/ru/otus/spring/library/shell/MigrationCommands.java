package ru.otus.spring.library.shell;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.library.service.RollbackMigrationService;

import static ru.otus.spring.library.config.JobConfig.IMPORT_AUTHORS_JOB_NAME;

@ShellComponent
@RequiredArgsConstructor
@Slf4j
public class MigrationCommands {

    private final JobLauncher jobLauncher;
    private final JobOperator jobOperator;

    private final RollbackMigrationService rollbackMigrationService;

    private final Job migrateAuthorsJob;
    private final JobExplorer jobExplorer;


    @ShellMethod(value = "showInfo", key = "i")
    public void showInfo() {
        System.out.println(jobExplorer.getJobNames());
        System.out.println(jobExplorer.getLastJobInstance(IMPORT_AUTHORS_JOB_NAME));
    }

    @ShellMethod(value = "rollback", key = {"r", "rollback"})
    public void rollbackMigration() {
        rollbackMigrationService.rollbackBooks();
        rollbackMigrationService.rollbackAuthors();
        log.info("Migration rolled back");
    }

    @ShellMethod(value = "startMigrationJobWithJobLauncher", key = {"m", "migration"})
    public void startMigrationJobWithJobLauncher() throws Exception {
        JobExecution execution = jobLauncher.run(migrateAuthorsJob, new JobParametersBuilder()
                .toJobParameters());
        System.out.println(execution);
    }
}
