package com.example.demo.controller;

import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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
        homeController.saveUser();
        //when

        //then
        System.out.println(">>> " + userRepository.findUserById(1L).get());
    }
}