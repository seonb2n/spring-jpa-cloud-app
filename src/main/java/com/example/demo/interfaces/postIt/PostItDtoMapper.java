package com.example.demo.interfaces.postIt;

import com.example.demo.domain.postIt.PostItCommand;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PostItDtoMapper {

    PostItCommand.RegisterPostIt of(PostItDto.RegisterRequest registerRequest);

}
