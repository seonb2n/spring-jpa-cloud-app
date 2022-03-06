package com.example.demo.domain.project;

import com.example.demo.domain.project.task.Task;
import com.example.demo.domain.project.task.action.Action;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ProjectInfoMapper {

    ProjectInfo.Main of(Project project, List<Task> taskList);

    ProjectInfo.TaskInfo of(Task task);

    ProjectInfo.ActionInfo of(Action action);

}
