package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;

import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;
   private final UserMapper userMapper;

   private final ProjectService projectService;

   private final TaskService taskService;
   private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, ProjectService projectService, TaskService taskService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.projectService = projectService;
        this.taskService = taskService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> listAllUser() {

        List<User> userList = userRepository.findAll(Sort.by("firstName"));//SELECT * FROM USERS WHERE DELETED = FALSE
        //convert entity to dto - We will use Mapper to convert
        return userList.stream().map(userMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {
        User user = userRepository.findByUserName(username);
        return userMapper.convertToDto(user);
    }



    @Override
    public UserDTO update(UserDTO dto) {
        //Find current user
        User user = userRepository.findByUserName(dto.getUserName());
        //Map updated user dto to entity object
        User convertedUser = userMapper.convertToEntity(dto);
        //set id to converted object
        convertedUser.setId(user.getId());
        //save updated user
        userRepository.save(convertedUser);

        return findByUserName(dto.getUserName());

    }

    @Override
    @Transactional
    public void deleteByUserName(String username) {
          userRepository.deleteByUserName(username);
    }

    @Override
    public void delete(String username) {
        //I will not delete from db
        //I will change the isDeleted flag and keep the object in db
        User user = userRepository.findByUserName(username);
        if(checkIfUserCanBeDeleted(user)){
            user.setIsDeleted(true);
            user.setUserName(user.getUserName()+"-"+user.getId());
            userRepository.save(user);

        }
    }

    private boolean checkIfUserCanBeDeleted(User user){
        switch(user.getRole().getDescription()){
            case "Manager":
                List<ProjectDTO>projectDTOSList = projectService.readAllByAssignedManager(user);
                return projectDTOSList.size()==0;
            case "Employee":
                List<TaskDTO>taskDTOSList = taskService.readAllByAssignedEmployee(user);
                return taskDTOSList.size()==0;
            default:
                return true;

        }
    }
    @Override
    public void save(UserDTO dto) {
        dto.setEnabled(true);
        User obj = userMapper.convertToEntity(dto);
        obj.setPassWord(passwordEncoder.encode(obj.getPassWord()));
        userRepository.save(obj);
    }

    @Override
    public List<UserDTO> listAllByRole(String role) {
        List<User>users = userRepository.findAllByRoleDescriptionIgnoreCase(role);
        return users.stream().map(user -> userMapper.convertToDto(user)).collect(Collectors.toList());
    }


}


