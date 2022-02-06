package com.example.demo.infrastructure.repository;

import com.example.demo.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface ProjectRepository extends JpaRepository<Project, Long> {


}
