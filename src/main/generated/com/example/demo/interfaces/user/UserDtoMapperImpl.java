package com.example.demo.interfaces.user;

import com.example.demo.domain.user.UserCommand.RegisterUser;
import com.example.demo.domain.user.UserCommand.RegisterUser.RegisterUserBuilder;
import com.example.demo.interfaces.user.UserDto.RegisterRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-30T21:08:13+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 14.0.2 (Oracle Corporation)"
)
@Component
public class UserDtoMapperImpl implements UserDtoMapper {

    @Override
    public RegisterUser of(RegisterRequest registerRequest) {
        if ( registerRequest == null ) {
            return null;
        }

        RegisterUserBuilder registerUser = RegisterUser.builder();

        registerUser.userEmail( registerRequest.getUserEmail() );
        registerUser.password( registerRequest.getPassword() );
        registerUser.userNickName( registerRequest.getUserNickName() );
        registerUser.startDayTime( registerRequest.getStartDayTime() );
        registerUser.endDayTime( registerRequest.getEndDayTime() );

        return registerUser.build();
    }
}
