package com.example.blog.services;

import com.example.blog.payloads.CategoryDTO;
import com.example.blog.payloads.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);

    void deleteCategory(Integer categoryId);

    CategoryDTO getCategory(Integer categoryId);

    CategoryResponse getCategories(Integer pageNumber, Integer pageSize);
}
