package com.soumya.blog_application.service;

import java.util.List;

import com.soumya.blog_application.payloads.CategoryDTO;

public interface CategoryService {
    public CategoryDTO addCategory(CategoryDTO categoryDTO);
    public CategoryDTO updateCategory(CategoryDTO categoryDTO,Integer id);
    public CategoryDTO deleteCategory(Integer id);
    public CategoryDTO findCategoryById(Integer id);
    public List<CategoryDTO> findAllCategory();

}
