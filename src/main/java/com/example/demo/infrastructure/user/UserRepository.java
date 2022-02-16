package com.example.demo.infrastructure.user;

import com.example.demo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserToken(String userToken);

    Optional<User> findUserByUserEmail(String userEmail);
}
