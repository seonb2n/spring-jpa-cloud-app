package com.example.demo.infrastructure.project;

import com.example.demo.domain.project.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findProjectByProjectToken(String projectToken);

    List<Project> findAllByUserToken(String userToken);

    @EntityGraph(attributePaths = {"user"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("select p from Project p left join p.user where p.user.id = :userId")
    Page<Project> findAllPage(Pageable pageable, @Param("userId") Long userId);
}
