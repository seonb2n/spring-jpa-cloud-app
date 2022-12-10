package com.example.demo.domain.project.service;

import com.example.demo.domain.project.ProjectCommand;
import com.example.demo.domain.project.ProjectInfo;
import com.example.demo.domain.user.User;

public interface ProjectService {

    /**
     * 새로운 project 를 등록하는 메서드
     * @param registerProject
     * @return 프로젝트 고유 token
     */
    String registerProject(ProjectCommand.RegisterProject registerProject);

    /**
     * 초기 회원 가입 시 태스크 등록을 받을 수 있도록 빈 프로젝트를 생성하는 메서드
     * @param userToken
     * @return 프로젝트 고유 token
     */
    String registerNoneProject(String userToken);

    /**
     * 프로젝트의 내용을 업데이트하는 메서드
     * @param updateProject
     * @return 프로젝트 고유 token
     */
    String updateProject(ProjectCommand.UpdateProject updateProject);

    /**
     * task 를 등록하는 메서드
     * @param registerTask
     * @return 태스크 고유 token
     */
    String registerTask(ProjectCommand.RegisterTask registerTask);

    /**
     * task 를 업데이트하는 메서드
     * @param updateTask
     * @return 태스크 고유 token
     */
    String updateTask(ProjectCommand.UpdateTask updateTask);

    /**
     * task 하위의 action 을 등록하는 메서드
     * @param registerAction
     * @return 액션 고유 token
     */
    String registerAction(ProjectCommand.RegisterAction registerAction);

    /**
     * 프로젝트 고유 token 으로 프로젝트를 가져오는 메서드
     * @param projectToken
     * @return ProjectInfo
     */
    ProjectInfo.Main retrieveProject(String projectToken);}
