package com.example.blog.services.impl;

import com.example.blog.entities.Category;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.payloads.CategoryDTO;
import com.example.blog.repositories.CategoryRepo;
import com.example.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = this.modelMapper.map(categoryDTO, Category.class);
        Category createdCategory = categoryRepo.save(category);
        return modelMapper.map(createdCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));

        category.setCategoryDescription(categoryDTO.getCategoryDescription());
        category.setCategoryTitle(categoryDTO.getCategoryTitle());

        Category updatedCategory = this.categoryRepo.save(category);
        return this.modelMapper.map(updatedCategory, CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("category", "category id", categoryId));
        this.categoryRepo.delete(category);
    }

    @Override
    public CategoryDTO getCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("categoty", "category id", categoryId));
        return this.modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getCategories() {
        List<Category> categories = this.categoryRepo.findAll();
        List<CategoryDTO> categoryDTOS = categories.stream().map( category -> modelMapper.map(category, CategoryDTO.class)).toList();
        return categoryDTOS;
    }
}
