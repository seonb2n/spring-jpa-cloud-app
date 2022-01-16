package com.example.demo.service;

import com.example.demo.dto.UserEnrollDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User userEnrollService(UserEnrollDto userEnrollDto) {
        User user = new User();
        user.setUserEmail(userEnrollDto.getUserEmail());
        user.setUserNickName(userEnrollDto.getUserNickName());
        user.setPassword(userEnrollDto.getPassword());
        user.setStartDayTime(userEnrollDto.getStartDayTime());
        user.setEndDayTime(userEnrollDto.getEndDayTime());

        return userRepository.save(user);
    }
}
