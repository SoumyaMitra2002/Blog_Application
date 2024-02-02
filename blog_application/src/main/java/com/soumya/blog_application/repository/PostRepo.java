package com.soumya.blog_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soumya.blog_application.entity.Category;
import com.soumya.blog_application.entity.Post;
import com.soumya.blog_application.entity.User;



import java.util.List;


public interface PostRepo extends JpaRepository<Post,Integer>{

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByUserAndCategory(User u,Category c);

    List<Post> findByTitleContainingIgnoreCase(String keyword);
    
}
