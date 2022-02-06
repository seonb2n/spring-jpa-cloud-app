package com.example.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDto {
    //user login 시에 사용하는 dto
    private String userEmail;
    private String password;
    //jwt token 만료시에 사용하기 위한 token
    private String refreshToken;
}
