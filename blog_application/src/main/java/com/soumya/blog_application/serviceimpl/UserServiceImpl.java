package com.soumya.blog_application.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soumya.blog_application.entity.User;
import com.soumya.blog_application.exception.ResourseNotFoundException;
import com.soumya.blog_application.payloads.UserDTO;
import com.soumya.blog_application.repository.UserRepo;
import com.soumya.blog_application.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    public UserRepo userRepo; 
    
    @Autowired
    public ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO user) {
        User user1=this.dtoToUser(user);
        this.userRepo.save(user1);
        return this.userToDto(user1);
    }

    @Override
    public UserDTO updateUser(UserDTO user, Integer id) {
        User user1=this.userRepo.findById(id).orElseThrow(()-> new ResourseNotFoundException("User", "Id", id));
        if(user.getName()!=null){
            user1.setName(user.getName());
        }
        if(user.getEmail()!=null){
            user1.setEmail(user.getEmail());
        }
        if(user.getPassword()!=null){
            user1.setPassword(user.getPassword());
        }
        if(user.getAbout()!=null){
            user1.setAbout(user.getAbout());
        }
        User saved=this.userRepo.save(user1);
        UserDTO userDTO=this.userToDto(saved);
        return userDTO;
    }

    @Override
    public UserDTO getUserById(Integer id) {
        User user=this.userRepo.findById(id).orElseThrow(()-> new ResourseNotFoundException("User", "ID", id));
        UserDTO userDTO=this.userToDto(user);
        return userDTO;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> list=this.userRepo.findAll();
        List<UserDTO> list2=new ArrayList<>();
        // for(User user : list){
        //     UserDTO userDTO=this.userToDto(user);
        //     list2.add(userDTO);
        // }
        list.forEach(u-> list2.add(userToDto(u)));
        return list2;
    }

    @Override
    public UserDTO deleteUser(Integer id) {
        User user=this.userRepo.findById(id).orElseThrow(()-> new ResourseNotFoundException("User", "Id",id));
        UserDTO userDTO=this.userToDto(user);
        this.userRepo.delete(user);
        return userDTO;
    }
    
    private User dtoToUser(UserDTO userDTO){
        User user =this.modelMapper.map(userDTO, User.class);
        // user.setId(userDTO.getId());
        // user.setName(userDTO.getName());
        // user.setEmail(userDTO.getEmail());
        // user.setPassword(userDTO.getPassword());
        // user.setAbout(userDTO.getAbout());
        return user;
    }

    private UserDTO userToDto(User user){
        UserDTO userDTO =this.modelMapper.map(user,UserDTO.class);
        // userDTO.setId(user.getId());
        // userDTO.setName(user.getName());
        // userDTO.setEmail(user.getEmail());
        // userDTO.setPassword(user.getPassword());
        // userDTO.setAbout(user.getAbout());
        return userDTO;
    }
}
