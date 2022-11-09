package com.example.demo.job;

import com.example.demo.TestDatasourceConfig;
import com.example.demo.domain.project.Project;
import com.example.demo.domain.project.task.Task;
import com.example.demo.domain.user.User;
import com.example.demo.infrastructure.job.NotifyTaskBeforeEndJobConfig;
import com.example.demo.infrastructure.job.writer.SendNotificationItemWriter;
import com.example.demo.infrastructure.notification.MessageSendService;
import com.example.demo.infrastructure.notification.NotificationRepository;
import com.example.demo.infrastructure.project.ProjectRepository;
import com.example.demo.infrastructure.project.TaskRepository;
import com.example.demo.infrastructure.user.UserRepository;
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
import org.springframework.context.annotation.ComponentScan;
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
@EnableJpaRepositories("com.example.demo.infrastructure")
@ComponentScan({"com.example.demo.infrastructure.job.writer", "com.example.demo.infrastructure.notification"})
@ActiveProfiles("test")
@ContextConfiguration(classes = {NotifyTaskBeforeEndJobConfig.class, TestDatasourceConfig.class})
public class NotifyTaskBeforeEndJobConfigTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageSendService messageSendService;

    @Autowired
    private SendNotificationItemWriter sendNotificationItemWriter;

    private static final LocalDateTime tomorrow = LocalDateTime.now().plusDays(1L);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @DisplayName("Task 만료 전 알람이 가도록 하는 Batch Test")
    @Test
    public void givenExpireTaskBeforeDat_whenExpireTaskBeforeDay_thenSendNotification() throws Exception {
        //given
        addTaskEntities(10);

        //when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        JobInstance jobInstance = jobExecution.getJobInstance();

        //then
        assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
        assertEquals(10, notificationRepository.findAll().size());
    }

    public void addTaskEntities(int size) {
        User user = new User("userEmail", "userPw", "userName", null, null);
        userRepository.save(user);
        Project testProject = Project.builder()
                .projectName("test-project")
                .user(user)
                .userToken("user_1234")
                .projectName("test-project1234")
                .build();
        testProject.changeProjectToken("project_1234");
        projectRepository.save(testProject);

        List<Task> taskList = new ArrayList<Task>();
        for (int i = 0; i < size; i++) {
            Task task = Task.builder()
                    .taskName("task" + i)
                    .startDayTime(LocalDateTime.now().minusDays(1L))
                    .endDayTime(tomorrow)
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
