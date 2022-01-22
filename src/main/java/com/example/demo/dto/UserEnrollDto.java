package com.example.demo.dto;

import com.example.demo.entity.UserAuthority;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEnrollDto {

    @NotBlank(message = "이메일은 필수 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String userEmail;

    @NotBlank(message = "비밀번호는 필수 값입니다.")
    private String password;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String userNickName;

    private String startDayTime;
    private String endDayTime;

    private String userAuthority;
}
