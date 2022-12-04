package com.example.demo.repository;

import com.example.demo.TestDatasourceConfig;
import com.example.demo.domain.project.Project;
import com.example.demo.domain.user.User;
import com.example.demo.infrastructure.project.ProjectRepository;
import com.example.demo.infrastructure.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * User 조회와 관련된 N + 1 문제 해결을 위한 쿼리 테스트 입니다.
 */
@ContextConfiguration(classes = {TestDatasourceConfig.class})
@SpringBootTest
public class UserRepositoryQueryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @DisplayName("User 를 단일 조회할 때 Query 문은 몇 개가 날아가는지 test")
    @Test
    public void givenNothing_whenFindSingleUser_thenReturnUser() throws Exception {
        //given

        //when
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);

        //then
        //fetch=Lazy 이므로 user 조회 한 번만 발생한다.
        System.out.println("== Query end ==");
        System.out.println(user.getUserNickName());
    }

    @DisplayName("fetch=Lazy 일 때 user 의 Project 를 조회하면 Query 문은 몇 개가 날아가는지 test")
    @Test
    @Transactional
    public void givenLazyFetch_whenFindUserProject_thenReturnProject() throws Exception {
        //given

        //when
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);

        //then
        // 쿼리 문이 user 조회에 한번, project 조회에 N번 발생한다.
        System.out.println(user.getProjectList().size());
        System.out.println("== Query end ==");
    }

    @DisplayName("Join Fetch 를 사용한 경우 Query 문이 몇 개가 날아가는지 test")
    @Test
    public void givenFindUserJoinFetch_whenFindUser_thenReturnUserAndProject() throws Exception {
        //given

        //when
        User user = userRepository.findUserUsingFetchByUserId(1L).orElseThrow(RuntimeException::new);

        //then
        // 쿼리 문이 1번만 발생한다.
        System.out.println(user.getProjectList().size());
        System.out.println("== Query end ==");
    }

    @DisplayName("Entity Graph 를 사용한 경우 Query 문이 몇 개가 날아가는지 test")
    @Test
    public void givenFindUserEntityGraph_whenFindUser_thenReturnUserAndProject() throws Exception {
        //given

        //when
        User user = userRepository.findUserUsingEntityGraphByUserId(1L).orElseThrow(RuntimeException::new);

        //then
        // 쿼리 문이 1번만 발생한다.
        System.out.println(user.getProjectList().size());
        System.out.println("== Query end ==");
    }

    @DisplayName("User Page 조회시 fetch join 만을 사용하면 Query 문 몇 개가 날아가는지 test")
    @Test
    public void givenFindUserPage_whenFindUserPage_thenReturnUsr() throws Exception {
        //given

        //when
        Page<User> userPage = userRepository.findUserPageUsingEntityGraphByUserId(PageRequest.of(0, 1), 1L);

        //then
        //applying in memory! 경고가 발생한다.
        System.out.println(userPage);
        System.out.println("== Query end ==");
    }

    @DisplayName("Project Page 조회시 Query 문 몇 개가 날아가는지 test")
    @Test
    public void givenFindProjectPage_whenFindProjectPage_thenReturnProject() throws Exception {
        //given

        //when
        Page<Project> projectPage = projectRepository.findAllPage(PageRequest.of(0, 1), 1L);

        //then
        // 쿼리 문이 1번만 발생한다.
        System.out.println(projectPage);
        System.out.println("== Query end ==");
    }

    @DisplayName("지연 로딩으로 연관된 field 를 가져올 때, getter 를 사용하면 null 반환을 방지하는지 test")
    @Test
    public void givenUser_whenFindUserProjectUsingGetter_thenReturnProject() throws Exception {
        //given

        //when
        Optional<User> user = userRepository.findUserUsingFetchByUserId(1L);

        //then
        assertEquals("test-user", user.get().getUserNickName());
        assertNotNull(user.get().getProjectList());
    }
}
