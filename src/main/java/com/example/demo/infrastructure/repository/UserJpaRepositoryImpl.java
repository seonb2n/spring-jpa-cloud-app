package com.example.demo.infrastructure.repository;

import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
public class UserJpaRepositoryImpl implements UserRepositoryCustom{

    private final EntityManager em;

}
