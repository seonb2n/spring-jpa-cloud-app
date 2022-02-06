package com.example.demo.controller;

import com.example.demo.domain.dto.UserEnrollDto;
import com.example.demo.domain.dto.UserLoginDto;
import com.example.demo.domain.entity.User;
import com.example.demo.infrastructure.repository.security.JWTUtil;
import com.example.demo.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/enrollUser")
    public User enrollUser(@RequestBody UserEnrollDto userEnrollDtoForm) {
        UserEnrollDto userEnrollDto = UserEnrollDto.builder()
                .userEmail(userEnrollDtoForm.getUserEmail())
                .userNickName(userEnrollDtoForm.getUserNickName())
                .password(userEnrollDtoForm.getPassword())
                .userAuthority("ROLE_USER")
                .startDayTime(userEnrollDtoForm.getStartDayTime())
                .endDayTime(userEnrollDtoForm.getEndDayTime())
                .build();

        return userService.userEnrollService(userEnrollDto);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginDto userLoginDto, HttpServletResponse response) {
        if(userLoginDto.getRefreshToken() != null) {
         //token 을 사용해서 로그인
            return null;
        } else {
            User user = userService.logIn(userLoginDto.getUserEmail(), userLoginDto.getPassword());
            response.setHeader("auth_token", "bearer " + JWTUtil.makeAuthToken(user));
            response.setHeader("refresh_token", "bearer " + JWTUtil.makeRefreshToken(user));
            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            return ResponseEntity.ok().body("로그인 성공!");
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/greeting")
    public String greeting(Authentication authentication) {
        return "hello " + authentication.getName();
    }
}
