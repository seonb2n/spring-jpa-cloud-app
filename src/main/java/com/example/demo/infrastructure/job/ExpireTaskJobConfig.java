package com.example.demo.infrastructure.job;

import com.example.demo.domain.project.task.Task;
import com.example.demo.infrastructure.project.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Collections;

/**
 *  마감 기한이 끝난 Task 의 Status를 변경한다.
 */
@Configuration
@RequiredArgsConstructor
public class ExpireTaskJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final TaskRepository taskRepository;

    private final static int CHUNK_SIZE = 5;

    @Bean
    public Job expireTaskJob() {
        return this.jobBuilderFactory.get("expireTaskJob")
                .start(expireTaskStep())
                .build();
    }

    @Bean
    public Step expireTaskStep() {
        return this.stepBuilderFactory.get("expireTaskStep")
                .<Task, Task>chunk(CHUNK_SIZE)
                .reader(expireTaskReader())
//                .processor(expireTaskProcessor())
//                .writer(expireTaskWriter())
                .build();
    }

    @Bean
    @StepScope
    public RepositoryItemReader<Task> expireTaskReader() {
        return new RepositoryItemReaderBuilder<Task>()
                .name("expireTaskItemReader")
                .repository(taskRepository)
                .methodName("findAllByStatus")
                .pageSize(CHUNK_SIZE)
                .arguments(Task.Status.ONGOING)
                .sorts(Collections.singletonMap("id", Sort.Direction.ASC))
                .build();

    }
}
