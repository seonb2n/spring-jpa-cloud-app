package com.example.demo.controller;

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

}