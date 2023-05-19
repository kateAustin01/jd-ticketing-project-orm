package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Project;
import com.cydeo.entity.User;
import com.cydeo.enums.Status;
import com.cydeo.mapper.ProjectMapper;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.ProjectRepository;
import com.cydeo.repository.TaskRepository;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

  private final   ProjectRepository projectRepository;
  private final   ProjectMapper projectMapper;


  UserService userService;

  UserMapper userMapper;

  TaskRepository taskRepository;

  TaskService taskService;

  public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper, UserService userService, UserMapper userMapper, TaskRepository taskRepository, TaskService taskService) {
    this.projectRepository = projectRepository;
    this.projectMapper = projectMapper;
    this.userService = userService;
    this.userMapper = userMapper;
    this.taskRepository = taskRepository;
    this.taskService = taskService;
  }

  @Override
    public ProjectDTO getByProjectCode(String code) {
        Project project = projectRepository.findByProjectCode(code);
       return projectMapper.convertToDto(project);

    }

    @Override
    public List<ProjectDTO> listAllProjects() {
    return  projectRepository.findAll().stream().map(project -> projectMapper.convertToDto(project)).collect(Collectors.toList());

    }

    @Override
    public void save(ProjectDTO dto) {
      dto.setProjectStatus(Status.OPEN);
      Project project = projectMapper.convertToEntity(dto);
      projectRepository.save(project);
    }


    @Override
    public void update(ProjectDTO dto) {
      Project project = projectRepository.findByProjectCode(dto.getProjectCode());
      Project convertedProject = projectMapper.convertToEntity(dto);
      convertedProject.setId(project.getId());
      convertedProject.setProjectStatus(project.getProjectStatus());
      projectRepository.save(convertedProject);
    }

    @Override
    public void delete(String code) {
      Project project = projectRepository.findByProjectCode(code);
      project.setIsDeleted(true);
      project.setProjectCode(project.getProjectCode()+"-"+project.getId());
       projectRepository.save(project);
       taskService.deleteByProject(projectMapper.convertToDto(project));

    }

  @Override
  public void complete(String code) {
    Project project = projectRepository.findByProjectCode(code);
    project.setProjectStatus(Status.COMPLETE);

    projectRepository.save(project);

    taskService.completeByProject(projectMapper.convertToDto(project));
  }

  @Override
  public List<ProjectDTO> listAllProjectsDetails() {

    UserDTO currentUserDTO = userService.findByUserName("harold@manager.com");
    User user =  userMapper.convertToEntity(currentUserDTO);
    List<Project>list = projectRepository.findAllByAssignedManager(user);
    return list.stream().map(project -> {

            ProjectDTO obj = projectMapper.convertToDto(project);
            obj.setUnfinishedTaskCounts(taskRepository.totalNonCompletedTasks(project.getProjectCode()));
            obj.setCompleteTaskCounts(taskRepository.totalCompletedTasks(project.getProjectCode()));
            return obj;


    }).collect(Collectors.toList());

  }

  @Override
  public List<ProjectDTO> readAllByAssignedManager(User assignedManager) {
    List<Project>list = projectRepository.findAllByAssignedManager(assignedManager);
    return list.stream().map(projectMapper::convertToDto).collect(Collectors.toList());
  }


}
