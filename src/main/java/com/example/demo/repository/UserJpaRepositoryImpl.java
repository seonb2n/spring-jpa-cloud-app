package com.example.demo.repository;

import com.example.demo.dto.UserEnrollDto;
import com.example.demo.entity.User;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaRepositoryImpl implements UserRepositoryCustom{

    private final EntityManager em;

}
