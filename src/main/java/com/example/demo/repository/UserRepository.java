package com.example.demo.repository;

import com.example.demo.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom{

    public void deleteUserByUserEmail(String userEmail);

    public Optional<User> findUserByUserEmail(String userEmail);

    public Optional<User> findUserById(Long id);
}
