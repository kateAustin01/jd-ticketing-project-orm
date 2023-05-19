package com.cydeo.mapper;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.RoleDTO;
import com.cydeo.entity.Project;
import com.cydeo.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    private final ModelMapper modelMapper;

    public ProjectMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    //method: convertToEntity
    public Project convertToEntity(ProjectDTO projectDTO){
        return  modelMapper.map(projectDTO,Project.class);
    }




    //method: convertToDto
    public ProjectDTO convertToDto(Project entity){
        return modelMapper.map(entity,ProjectDTO.class);
    }
}
