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

    // TODO BatchSize 로 해결하는 방법 구현하기
    // https://velog.io/@jinyoungchoi95/JPA-%EB%AA%A8%EB%93%A0-N1-%EB%B0%9C%EC%83%9D-%EC%BC%80%EC%9D%B4%EC%8A%A4%EA%B3%BC-%ED%95%B4%EA%B2%B0%EC%B1%85

}
