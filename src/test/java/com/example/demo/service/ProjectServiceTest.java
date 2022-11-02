package com.example.demo.service;

import com.example.demo.domain.project.Project;
import com.example.demo.domain.project.ProjectCommand;
import com.example.demo.domain.project.ProjectInfoMapper;
import com.example.demo.domain.project.service.*;
import com.example.demo.domain.project.task.Task;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.service.UserReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    private static ProjectService projectService;

    private static final ProjectReader projectReader = mock(ProjectReader.class);
    private static final ProjectStore projectStore = mock(ProjectStore.class);
    private static final UserReader userReader = mock(UserReader.class);
    private static final ProjectSeriesRegisterFactory projectSeriesRegisterFactory = mock(ProjectSeriesRegisterFactory.class);
    private static final ProjectSeriesUpdateFactory projectSeriesUpdateFactory = mock(ProjectSeriesUpdateFactory.class);
    private static final ProjectInfoMapper projectInfoMapper = mock(ProjectInfoMapper.class);

    private static final String userToken = "testUserToken";
    private static final String testPjtName = "testPjtName";
    private static final String endDayTime = "testEndDayTime";
    private static final String testPjtToken = "testPjtToken";
    private static ProjectCommand.RegisterTask registerTask = ProjectCommand.RegisterTask.builder()
            .taskName("testTask")
            .importance("HIGH")
            .projectToken(null)
            .startDayTime(LocalDateTime.now())
            .endDayTime(LocalDateTime.now().plusDays(1L))
            .registerActionList(null)
            .build();
    private static Project testProject;
    private static String taskToken;
    private static final String taskName = "testTaskName";
    private static final String taskImportance = "HIGH";

    private static Task testTask;
    private static User user;

    @BeforeAll
    static void setUp() {
        testTask = new Task(taskName, taskImportance, LocalDateTime.now(), LocalDateTime.now().plusDays(1L), null, testPjtToken, null, "ONGOING");
        projectService = new ProjectServiceImpl(projectReader, projectStore, userReader, projectSeriesRegisterFactory, projectSeriesUpdateFactory, projectInfoMapper);
        user = new User("userEmail", "userPw", "userName", "0900", "1800");
        user.changeUserToken(userToken);
        testProject = new Project(testPjtName, endDayTime, List.of(testTask), user, userToken);
        testProject.changeProjectToken(testPjtToken);
        taskToken = testTask.getTaskToken();
    }

    @DisplayName("프로젝트 등록 테스트")
    @Test
    public void givenProjectRegisterCommand_whenRegisterProject_thenReturnProjectToken() throws Exception {
        //given
        ProjectCommand.RegisterProject registerProject = ProjectCommand.RegisterProject.builder()
                .userToken(userToken)
                .projectName(testPjtName)
                .endDayTime(endDayTime)
                .registerTaskList(List.of(registerTask))
                .build();
        given(userReader.getUserWithUserToken(userToken)).willReturn(user);
        given(projectSeriesRegisterFactory.storeProject(any(User.class), any(ProjectCommand.RegisterProject.class))).willReturn(testProject);

        //when
        var projectToken = projectService.registerProject(registerProject);

        //then
        assertNotNull(projectToken);
        assertEquals(testPjtToken, projectToken);
    }

    @DisplayName("프로젝트 등록 테스트 = 초기 공백 테스트 생성")
    @Test
    public void givenUserToken_whenRegisterNoneProject_thenReturnProjectToken() throws Exception {
        //given
        given(userReader.getUserWithUserToken(any(String.class))).willReturn(user);
        given(projectStore.store(any(Project.class))).willReturn(testProject);

        //when
        var projectToken = projectService.registerNoneProject(userToken);

        //then
        assertNotNull(projectToken);
        assertEquals(testPjtToken, projectToken);
    }

    @DisplayName("Task 등록 테스트")
    @Test
    public void givenRegisterTaskCommand_whenRegisterTask_thenReturnTaskToken() throws Exception {
        //given
        ProjectCommand.RegisterTask registerTaskCommand = ProjectCommand.RegisterTask.builder()
                .taskName(taskName)
                .projectToken(testPjtToken)
                .startDayTime(LocalDateTime.now())
                .endDayTime(LocalDateTime.now().plusDays(1L))
                .importance(taskImportance)
                .build();
        given(projectReader.getProjectWithToken(testPjtToken)).willReturn(testProject);
        given(projectSeriesRegisterFactory.storeTask(any(Project.class), any(ProjectCommand.RegisterTask.class))).willReturn(testTask);

        //when
        var resultTaskToken = projectService.registerTask(registerTaskCommand);

        //then
        assertNotNull(resultTaskToken);
        assertEquals(taskToken, resultTaskToken);
    }

    @DisplayName("task 업데이트 테스트")
    @Test
    public void givenUpdateTaskCommand_whenUpdateTask_thenReturnTaskToken() throws Exception {
        //given
        ProjectCommand.UpdateAction updateActionCommand = ProjectCommand.UpdateAction.builder()
                .actionStatus("UNDONE")
                .taskToken(taskToken)
                .content("action content")
                .build();

        ProjectCommand.UpdateTask updateTaskCommand = ProjectCommand.UpdateTask.builder()
                .taskName("newTestTask")
                .taskToken(taskToken)
                .projectToken(testPjtToken)
                .importance("LOW")
                .updateActionList(List.of(updateActionCommand))
                .build();
        given(projectReader.getProjectWithToken(testPjtToken)).willReturn(testProject);
        given(projectSeriesUpdateFactory.updateTask(any(Project.class), any(ProjectCommand.UpdateTask.class))).willReturn(testTask);

        //when
        var resultTaskToken = projectService.updateTask(updateTaskCommand);

        //then
        assertNotNull(resultTaskToken);
        assertEquals(taskToken, resultTaskToken);
    }

}
