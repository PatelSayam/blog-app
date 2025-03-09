package com.example.blog.services.impl;

import com.example.blog.entities.Category;
import com.example.blog.exceptions.ResourceNotFoundException;
import com.example.blog.payloads.CategoryDTO;
import com.example.blog.payloads.CategoryResponse;
import com.example.blog.repositories.CategoryRepo;
import com.example.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
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
    public CategoryResponse getCategories(Integer pageNumber, Integer pageSize) {
       Pageable pageable = PageRequest.of(pageNumber, pageSize);

       Page<Category> pageCategory = categoryRepo.findAll(pageable);

       List<Category> allCategory = pageCategory.getContent();

       List<CategoryDTO> CategoryDTOs = allCategory.stream().map(category -> modelMapper.map(category, CategoryDTO.class)).toList();

       CategoryResponse categoryResponse = new CategoryResponse();

       categoryResponse.setContent(CategoryDTOs);
       categoryResponse.setPageNumber(pageCategory.getNumber());
       categoryResponse.setPageSize(pageCategory.getSize());
       categoryResponse.setTotalElements(pageCategory.getTotalElements());

       categoryResponse.setTotalPage(pageCategory.getTotalPages());
       categoryResponse.setLastPage(pageCategory.isLast());

       return categoryResponse;

    }
}
