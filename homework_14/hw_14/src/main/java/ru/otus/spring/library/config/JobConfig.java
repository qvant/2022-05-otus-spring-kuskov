package ru.otus.spring.library.config;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.lang.NonNull;
import ru.otus.spring.library.domain.result.AuthorTarget;
import ru.otus.spring.library.domain.result.BookTarget;
import ru.otus.spring.library.domain.source.Author;
import ru.otus.spring.library.domain.source.Book;
import ru.otus.spring.library.repository.source.AuthorRepository;
import ru.otus.spring.library.repository.source.BookRepository;
import ru.otus.spring.library.service.AuthorTransform;
import ru.otus.spring.library.service.BookTransform;
import ru.otus.spring.library.service.CheckTargetState;

import java.util.HashMap;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class JobConfig {
    public static final String IMPORT_LIBRARY_JOB_NAME = "importLibraryJob";
    private static final int CHUNK_SIZE = 5;
    private final Logger logger = LoggerFactory.getLogger("Batch");
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final AuthorTransform authorTransform;
    private final CheckTargetState checkTargetState;

    @StepScope
    @Bean
    public RepositoryItemReader<Author> authorRepositoryItemReader(AuthorRepository authorRepository) {
        RepositoryItemReader<Author> reader = new RepositoryItemReader<>();
        reader.setRepository(authorRepository);
        reader.setMethodName("findAll");
        HashMap<String, Sort.Direction> sorts = new HashMap<>();
        sorts.put("id", Sort.Direction.ASC);
        reader.setSort(sorts);
        return reader;
    }

    @StepScope
    @Bean
    public RepositoryItemReader<Book> bookRepositoryItemReader(BookRepository bookRepository) {
        RepositoryItemReader<Book> reader = new RepositoryItemReader<>();
        reader.setRepository(bookRepository);
        reader.setMethodName("findAll");
        HashMap<String, Sort.Direction> sorts = new HashMap<>();
        sorts.put("id", Sort.Direction.ASC);
        reader.setSort(sorts);
        return reader;
    }

    @Bean
    public ItemProcessor<Author, AuthorTarget> authorProcessor(AuthorTransform authorTransform) {
        return authorTransform::convert;
    }

    @Bean
    public MongoItemWriter<AuthorTarget> authorTargetMongoItemWriter(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<AuthorTarget>().template(mongoTemplate).collection("authors").build();
    }

    @Bean
    public ItemProcessor<Book, BookTarget> bookProcessor(BookTransform bookTransform) {
        return bookTransform::convert;
    }

    @Bean
    public MongoItemWriter<BookTarget> bookTargetMongoItemWriter(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<BookTarget>().template(mongoTemplate).collection("books").build();
    }

    @Bean
    public Job importAuthorsJob(Step migrateAuthorsStep, Step migrateBooksStep,
                                Step checkMigrationPossible) {
        return jobBuilderFactory.get(IMPORT_LIBRARY_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .flow(checkMigrationPossible)
                .next(migrateAuthorsStep)
                .next(migrateBooksStep)
                .end()
                .listener(new JobExecutionListener() {
                    @Override
                    public void beforeJob(@NonNull JobExecution jobExecution) {
                        logger.info("Начало " + IMPORT_LIBRARY_JOB_NAME);
                    }

                    @Override
                    public void afterJob(@NonNull JobExecution jobExecution) {
                        logger.info("Конец " + IMPORT_LIBRARY_JOB_NAME);
                    }
                })
                .build();
    }

    @Bean
    public Step migrateAuthorsStep(RepositoryItemReader<Author> reader, MongoItemWriter<AuthorTarget> writer,
                                   ItemProcessor<Author, AuthorTarget> itemProcessor) {
        return stepBuilderFactory.get("step1")
                .<Author, AuthorTarget>chunk(CHUNK_SIZE)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .listener(new ItemReadListener<>() {
                    public void beforeRead() {

                        logger.info("Начало чтения авторов");
                    }

                    public void afterRead(@NonNull Author o) {
                        logger.info("Конец чтения авторов");
                    }

                    public void onReadError(@NonNull Exception e) {
                        logger.info("Ошибка чтения");
                    }
                })
                .listener(new ItemWriteListener<>() {
                    public void beforeWrite(@NonNull List list) {
                        logger.info("Начало записи авторов");
                    }

                    public void afterWrite(@NonNull List list) {
                        logger.info("Конец записи авторов");
                    }

                    public void onWriteError(@NonNull Exception e, @NonNull List list) {
                        logger.info("Ошибка записи");
                    }
                })
                .listener(new ItemProcessListener<>() {
                    public void beforeProcess(Author o) {
                        logger.info("Начало обработки авторов");
                    }

                    public void afterProcess(@NonNull Author o, AuthorTarget o2) {
                        logger.info("Конец обработки авторов");
                    }

                    public void onProcessError(@NonNull Author o, @NonNull Exception e) {
                        logger.info("Ошибка обработки");
                    }
                })
                .listener(new ChunkListener() {
                    public void beforeChunk(@NonNull ChunkContext chunkContext) {
                        logger.info("Начало пачки авторов");
                    }

                    public void afterChunk(@NonNull ChunkContext chunkContext) {
                        logger.info("Конец пачки авторов");
                    }

                    public void afterChunkError(@NonNull ChunkContext chunkContext) {
                        logger.info("Ошибка пачки авторов");
                    }
                })
                .build();
    }

    @Bean
    public Step migrateBooksStep(RepositoryItemReader<Book> reader, MongoItemWriter<BookTarget> writer,
                                 ItemProcessor<Book, BookTarget> itemProcessor) {
        return stepBuilderFactory.get("step2")
                .<Book, BookTarget>chunk(CHUNK_SIZE)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .listener(new ItemReadListener<>() {
                    @Override
                    public void beforeRead() {
                        logger.info("Начало чтения книг 1");
                    }

                    public void afterRead(@NonNull Book o) {
                        logger.info("Конец чтения книг");
                    }

                    public void onReadError(@NonNull Exception e) {
                        logger.info("Ошибка книг");
                    }
                })
                .listener(new ItemWriteListener<>() {
                    public void beforeWrite(@NonNull List list) {
                        logger.info("Начало записи книг");
                    }

                    public void afterWrite(@NonNull List list) {
                        logger.info("Конец записи книг");
                    }

                    public void onWriteError(@NonNull Exception e, @NonNull List list) {
                        logger.info("Ошибка записи");
                    }
                })
                .listener(new ItemProcessListener<>() {
                    public void beforeProcess(Book o) {
                        logger.info("Начало обработки книг");
                    }

                    public void afterProcess(@NonNull Book o, BookTarget o2) {
                        logger.info("Конец обработки книг");
                    }

                    public void onProcessError(@NonNull Book o, @NonNull Exception e) {
                        logger.info("Ошибка обработки");
                    }
                })
                .listener(new ChunkListener() {
                    public void beforeChunk(@NonNull ChunkContext chunkContext) {
                        logger.info("Начало пачки книг");
                    }

                    public void afterChunk(@NonNull ChunkContext chunkContext) {
                        logger.info("Конец пачки книг");
                    }

                    public void afterChunkError(@NonNull ChunkContext chunkContext) {
                        logger.info("Ошибка пачки книг");
                    }
                })
                .build();
    }

    @Bean
    public MethodInvokingTaskletAdapter checkMigrationPossibleTasklet() {
        MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();

        adapter.setTargetObject(checkTargetState);
        adapter.setTargetMethod("checkMigrationPossible");

        return adapter;
    }

    @Bean
    public Step checkMigrationPossible() {
        return this.stepBuilderFactory.get("checkMigrationPossible")
                .tasklet(checkMigrationPossibleTasklet())
                .build();
    }

}
