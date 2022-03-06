package com.example.demo.interfaces.project;

import com.example.demo.domain.postIt.PostItCommand;
import com.example.demo.domain.project.ProjectCommand;
import com.example.demo.interfaces.postIt.PostItDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProjectDtoMapper {

    ProjectCommand.RegisterProject of(ProjectDto.RegisterProjectRequest registerProjectRequest);

    ProjectCommand.RegisterTask of(ProjectDto.RegisterTaskRequest registerTaskRequest);

    ProjectCommand.RegisterAction of(ProjectDto.RegisterActionRequest registerActionRequest);

}
