package com.soumya.blog_application.service;

import java.util.List;

import com.soumya.blog_application.payloads.UserDTO;

public interface UserService {
    UserDTO createUser(UserDTO user);
    UserDTO updateUser(UserDTO user,Integer id);
    UserDTO getUserById(Integer id);
    List<UserDTO> getAllUsers();
    UserDTO deleteUser(Integer id);
}
