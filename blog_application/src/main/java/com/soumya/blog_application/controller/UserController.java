package com.soumya.blog_application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.soumya.blog_application.payloads.UserDTO;
import com.soumya.blog_application.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/")
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO){
        UserDTO userDTO1=this.userService.createUser(userDTO);
        return new ResponseEntity<>(userDTO1,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable("id") Integer id){
        UserDTO userDTO1=this.userService.getUserById(id);
        return new ResponseEntity<>(userDTO1,HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO,@PathVariable Integer id){
        UserDTO user=this.userService.updateUser(userDTO, id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@Valid @PathVariable Integer id){
        UserDTO userDTO=this.userService.deleteUser(id);
        return new ResponseEntity<>(userDTO,HttpStatus.GONE);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> findallUser(){
        List<UserDTO> list=this.userService.getAllUsers();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
