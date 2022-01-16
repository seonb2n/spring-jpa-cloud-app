package com.example.demo.controller;

import com.example.demo.dto.UserEnrollDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/enrollUser")
    public User enrollUser(@RequestBody UserEnrollDto userEnrollDtoForm) {
        UserEnrollDto userEnrollDto = UserEnrollDto.builder()
                .userEmail(userEnrollDtoForm.getUserEmail())
                .userNickName(userEnrollDtoForm.getUserNickName())
                .password(userEnrollDtoForm.getPassword())
                .startDayTime(userEnrollDtoForm.getStartDayTime())
                .endDayTime(userEnrollDtoForm.getEndDayTime())
                .build();

        return userService.userEnrollService(userEnrollDto);
    }

}
