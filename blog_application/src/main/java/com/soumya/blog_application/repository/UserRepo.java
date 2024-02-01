package com.soumya.blog_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soumya.blog_application.entity.User;

public interface UserRepo extends JpaRepository<User,Integer>{

    
}
