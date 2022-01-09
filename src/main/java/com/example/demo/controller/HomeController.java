package com.example.demo.controller;

import com.example.demo.dto.UserEnrollDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.support.Level;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
@RequiredArgsConstructor
public class HomeController {

    private final UserRepository userRepository;

    @GetMapping("/saveUser")
    public User saveUser(UserEnrollDto userEnrollDto) {
        User user = User.builder()
                .userName(userEnrollDto.getUserName())
                .userLocation(userEnrollDto.getUserLocation())
                .level(Level.SILVER)
                .build();
        return userRepository.save(user);
    }
}
