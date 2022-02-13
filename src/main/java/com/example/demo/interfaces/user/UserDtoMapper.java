package com.example.demo.interfaces.user;

import com.example.demo.domain.user.UserCommand;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserDtoMapper {

    UserCommand.RegisterUser of(UserDto.RegisterRequest registerRequest);
}
