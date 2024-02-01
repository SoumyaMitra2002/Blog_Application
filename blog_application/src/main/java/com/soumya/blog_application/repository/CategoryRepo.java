package com.soumya.blog_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.soumya.blog_application.entity.Category;


public interface CategoryRepo extends JpaRepository<Category,Integer> {
    
}
