package com.soumya.blog_application.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soumya.blog_application.entity.Category;
import com.soumya.blog_application.exception.ResourseNotFoundException;
import com.soumya.blog_application.payloads.CategoryDTO;
import com.soumya.blog_application.repository.CategoryRepo;
import com.soumya.blog_application.service.CategoryService;

@Service
public class CategoryImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category category = this.modelMapper.map(categoryDTO, Category.class);
        this.categoryRepo.save(category);
        return this.modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer id) {
        Category category = this.categoryRepo.findById(id)
        .orElseThrow(() -> new ResourseNotFoundException("Category", "Id", id));
        if(categoryDTO.getCategoryTitle()!=null){
            category.setCategoryTitle(categoryDTO.getCategoryTitle());
        }
        if(categoryDTO.getCategoryDescription()!=null){
            category.setCategoryDescription(categoryDTO.getCategoryDescription());
        }
        

        Category saved = this.categoryRepo.save(category);
        return this.modelMapper.map(saved, CategoryDTO.class);

    }

    @Override
    public CategoryDTO deleteCategory(Integer id) {
        CategoryDTO categoryDTO = this.findCategoryById(id);
        this.categoryRepo.deleteById(id);
        return categoryDTO;
    }

    @Override
    public CategoryDTO findCategoryById(Integer id) {
        Category category = this.categoryRepo.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Category", "Id", id));
        return this.modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> findAllCategory() {

        List<CategoryDTO> result = new ArrayList<>();

        this.categoryRepo.findAll().forEach(c -> result.add(this.modelMapper.map(c, CategoryDTO.class)));
        return result;
    }

}
