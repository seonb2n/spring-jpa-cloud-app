package com.example.demo.controller;

import com.example.demo.dto.UserEnrollDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class HomeControllerTest {

    @Autowired
    private HomeController homeController;

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    EntityManager em;


    @Test
    public void userSaveTest() throws Exception {
        //given
        UserEnrollDto userEnrollDto = UserEnrollDto.builder()
                .userName("sbsb")
                .userLocation("Seoul")
                .build();

        //when
        homeController.saveUser(userEnrollDto);


        //then
        System.out.println(">>> " + userRepository.findUserById(1L).get().getCreatedAt());
        System.out.println(">>> " + userRepository.findUserById(1L).get().getUpdateAt());
        System.out.println(">>> " + userRepository.findUserById(1L).get());
    }

    @Test
    public void userUpdateTest() throws Exception {
        //given
        User user = User.builder()
                .userName("sbsb")
                .userLocation("Seoul")
                .build();

        User u1 = userRepository.save(user);

        //when
        u1.setUserName("sbsb-new");
        //영속성 컨택스트에 의해서 관리되는 user 는 이름을 바꾸기만 해도 update 가 자동으로 될 것이다.

        //then
        System.out.println(">>> " + userRepository.findUserById(1L).get());
    }
}