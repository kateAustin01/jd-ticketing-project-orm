package com.cydeo.mapper;

import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Role;
import com.cydeo.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    //if you want to use any method from ModelMapper class We need to injection
    private final ModelMapper modelMapper;

    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    //method: convertToEntity
    public Role convertToEntity(RoleDTO roleDTO){
        return  modelMapper.map(roleDTO,Role.class);
    }




    //method: convertToDto
    public RoleDTO convertToDto(Role entity){
        return modelMapper.map(entity,RoleDTO.class);
    }


}
