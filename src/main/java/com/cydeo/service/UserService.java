package com.cydeo.service;

import com.cydeo.dto.UserDTO;

import java.util.List;

public interface UserService {

  List<UserDTO>listAllUser();
  UserDTO findByUserName(String username);

   UserDTO update(UserDTO dto);
   void deleteByUserName(String username);
    void save(UserDTO dto);








}
