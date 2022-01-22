package com.example.demo.security;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.demo.controller.UserController;
import com.example.demo.dto.UserLoginDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.IOException;

public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private ObjectMapper objectMapper = new ObjectMapper();
    private UserService userService;
    private UserController userController;

    public JWTLoginFilter(AuthenticationManager authenticationManager, UserService userService) {
        super(authenticationManager);
        this.userService = userService;
        //필터가 작동하는 URL 설정
        //로그인 할 때 필터를 통과하면서 JWT 토큰을 검사할 것
        setFilterProcessesUrl("/api/user/logInWithToken");
    }

    @SneakyThrows //예외 처리를 위한 @
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UserLoginDto userLoginDto = objectMapper.readValue(request.getInputStream(), UserLoginDto.class);

        //처음 로그인을 시도
        if(userLoginDto.getRefreshToken() == null) {

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    userLoginDto.getUserEmail(), userLoginDto.getPassword(), userService.loadUserByUsername(userLoginDto.getUserEmail()).getAuthorities()
            );

            return getAuthenticationManager().authenticate(token);
        } else {
            VerifyResult verifyResult = JWTUtil.verify(userLoginDto.getRefreshToken());
            if(verifyResult.isSuccess()) {
                User user = (User) userService.loadUserByUsername(verifyResult.getEmail());
                return new UsernamePasswordAuthenticationToken(
                        user, user.getAuthorities()
                );
            } else if(!userService.loadUserByUsername(userLoginDto.getUserEmail()).getAuthorities().isEmpty()) {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        userLoginDto.getUserEmail(), userLoginDto.getPassword(), userService.loadUserByUsername(userLoginDto.getUserEmail()).getAuthorities()
                );
                return getAuthenticationManager().authenticate(token);

            }else {
                throw new TokenExpiredException("refresh Token expired");
            }
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        response.setHeader("auth_token", JWTUtil.makeAuthToken(user));
        response.setHeader("refresh_token", JWTUtil.makeRefreshToken(user));
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//        response.sendRedirect("/api/user/greeting");
        chain.doFilter(request, response);
    }
}
