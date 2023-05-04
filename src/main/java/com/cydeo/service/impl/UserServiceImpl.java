package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<UserDTO> listAllUser() {
 return null;
    }

    @Override
    public UserDTO findByUserName(String username) {
        return null;
    }



    @Override
    public UserDTO update(UserDTO dto) {
        return null;
    }

    @Override
    public void deleteByUserName(String username) {

    }

    @Override
    public void save(UserDTO dto) {

    }
}
