package com.ecommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.helper.ApiResponse;
import com.ecommerce.model.Category;
import com.ecommerce.service.CategoryService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/category")
public class CategoryController
{

    Logger logger = LoggerFactory.getLogger(CategoryController.class);

    // Constructor Injection
    private final CategoryService categoryService;

    CategoryController(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }


    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategies()
    {

        logger.info("All Categories returned");

        List<Category> allCategories = categoryService.getAllCategories();

        return ResponseEntity.ok(allCategories);
        
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id)
    {
        
        logger.info("Category fetched with id : " + id);

        Category category = categoryService.getCategoryById(id);

        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCateory(@PathVariable Long id)
    {
        logger.info("Category deleted with id " + id);

        categoryService.deleteCategoryById(id);

        ApiResponse<Void> response = new ApiResponse<>(true, "Category deleted successfully", null);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/addCategory")
    public ResponseEntity<Category> addCategor(@RequestBody Category category)
    {
        Category addedCategory = categoryService.addCategory(category);

        logger.info("New Category added with id : " + category.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(addedCategory);
    }

    @PutMapping("updateCategory/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category)
    {

        logger.info("Updated Category");        
        
        Category updatedCategory = categoryService.updateCategory(id, category);

        return ResponseEntity.ok(updatedCategory);

    }
 
}