package com.example.demo.infrastructure.repository;

import com.example.demo.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom{

    public void deleteUserByUserEmail(String userEmail);

    public Optional<User> findUserByUserEmail(String userEmail);

    public Optional<User> findUserById(Long id);
}
