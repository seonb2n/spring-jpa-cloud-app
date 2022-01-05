package com.example.demo.entity;

import lombok.Getter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    LocalDateTime createdAt;
    LocalDateTime updateAt;

}
