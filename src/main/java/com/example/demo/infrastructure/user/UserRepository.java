package com.example.demo.infrastructure.user;

import com.example.demo.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserToken(String userToken);

    Optional<User> findUserByUserEmail(String userEmail);

    @Query("select u from User u left join fetch u.projectList where u.id = :userId")
    Optional<User> findUserUsingFetchByUserId(@Param("userId") Long userId);

    @EntityGraph(attributePaths = {"projectList"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("select u from User u left join u.projectList where u.id = :userId")
    Optional<User> findUserUsingEntityGraphByUserId(@Param("userId") Long userId);

    @EntityGraph(attributePaths = {"projectList"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("select u from User u left join u.projectList where u.id = :userId")
    Page<User> findUserPageUsingEntityGraphByUserId(Pageable pageable, @Param("userId") Long userId);
}
