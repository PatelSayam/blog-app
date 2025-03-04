package com.example.blog.controllers;

import com.example.blog.payloads.ApiResponse;
import com.example.blog.payloads.CategoryDTO;
import com.example.blog.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO createCategory = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Integer categoryId) {
        CategoryDTO updateCategory = this.categoryService.updateCategory(categoryDTO, categoryId);
        return ResponseEntity.ok(updateCategory);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully", true), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(categoryService.getCategory(categoryId));
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }
}
