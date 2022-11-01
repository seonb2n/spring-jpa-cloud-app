package com.example.demo.job;

import com.example.demo.TestDatasourceConfig;
import com.example.demo.domain.project.Project;
import com.example.demo.domain.project.task.Task;
import com.example.demo.infrastructure.job.ExpireTaskJobConfig;
import com.example.demo.infrastructure.project.ProjectRepository;
import com.example.demo.infrastructure.project.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@SpringBatchTest
@EnableBatchProcessing
@EnableAutoConfiguration
@EntityScan("com.example.demo.domain")
@EnableJpaRepositories("com.example.demo.infrastructure.project")
@ActiveProfiles("test")
@ContextConfiguration(classes = {ExpireTaskJobConfig.class, TestDatasourceConfig.class})
public class ExpireTaskJobConfigTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    private static final LocalDateTime yesterday = LocalDateTime.now().minusDays(1L);

    @DisplayName("기간 만료 Task Batch Test")
    @Test
    public void givenEndedTask_whenBatchRun_thenTaskStatusChange() throws Exception {
        //given
        addTaskEntities(20);


        //when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        JobInstance jobInstance = jobExecution.getJobInstance();

        //then
        assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
        assertEquals("expireTaskJob", jobInstance.getJobName());

        var tasks = taskRepository.findAllByEndDayTimeBefore(yesterday, Pageable.ofSize(10));
        tasks.forEach(task -> assertEquals(Task.Status.FAIL, task.getStatus()));
    }


    public void addTaskEntities(int size) {
        Project testProject = Project.builder()
                .projectName("test-project")
                .userToken("user_1234")
                .projectName("test-project1234")
                .build();
        testProject.changeProjectToken("project_1234");
        projectRepository.save(testProject);

        List<Task> taskList = new ArrayList<Task>();
        for (int i = 0; i < size; i++) {
            Task task = Task.builder()
                    .taskName("task" + i)
                    .startDayTime(yesterday.minusDays(1L))
                    .endDayTime(yesterday)
                    .importance("HIGH")
                    .project(testProject)
                    .projectToken(testProject.getProjectToken())
                    .status("ONGOING")
                    .build();
            taskList.add(task);
        }
        taskRepository.saveAll(taskList);
    }

}
