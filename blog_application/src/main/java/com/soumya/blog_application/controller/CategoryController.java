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

import com.soumya.blog_application.payloads.ApiResponse;
import com.soumya.blog_application.payloads.CategoryDTO;
import com.soumya.blog_application.service.CategoryService;
import com.soumya.blog_application.serviceimpl.CategoryImpl;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    public CategoryService categoryService;
    
    @PostMapping
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO categoryDTO1=this.categoryService.addCategory(categoryDTO);
        return new ResponseEntity<>(categoryDTO1,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findCategoryById(@PathVariable Integer id){
        CategoryDTO categoryDTO=this.categoryService.findCategoryById(id);
        return new ResponseEntity<>(categoryDTO,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO,@PathVariable Integer id){
        CategoryDTO categoryDTO1=this.categoryService.updateCategory(categoryDTO, id);
        return new ResponseEntity<>(categoryDTO1,HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategory(){
        List<CategoryDTO> list=this.categoryService.findAllCategory();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id){
        CategoryDTO categoryDTO=this.categoryService.findCategoryById(id);
        this.categoryService.deleteCategory(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully",true),HttpStatus.ACCEPTED);
    }
}
