package com.example.demo.security;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.demo.dto.UserLoginDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private ObjectMapper objectMapper = new ObjectMapper();
    private UserService userService;

    public JWTLoginFilter(AuthenticationManager authenticationManager, UserService userService) {
        super(authenticationManager);
        this.userService = userService;
        //필터가 작동하는 URL 설정
        //로그인 할 때 필터를 통과하면서 JWT 토큰을 발급해줄 것
        setFilterProcessesUrl("/login");
    }

    @SneakyThrows //예외 처리를 위한 @
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UserLoginDto userLoginDto = objectMapper.readValue(request.getInputStream(), UserLoginDto.class);

        //처음 로그인을 시도
        if(userLoginDto.getRefreshToken() == null) {

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    userLoginDto.getUserEmail(), userLoginDto.getPassword(), null
            );

            return getAuthenticationManager().authenticate(token);
        } else {
            VerifyResult verifyResult = JWTUtil.verify(userLoginDto.getRefreshToken());
            if(verifyResult.isSuccess()) {
                User user = (User) userService.loadUserByUsername(verifyResult.getEmail());
                return new UsernamePasswordAuthenticationToken(
                        user, user.getAuthorities()
                );
            } else {
                throw new TokenExpiredException("refresh Token expired");
            }
        }
    }
}
