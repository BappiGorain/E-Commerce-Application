package com.ecommerce.service;

import java.util.List;

import com.ecommerce.model.Category;

public interface CategoryService
{
    public List<Category> getAllCategory();

    public Category getCategoryById(Long id);

    public Category deleteCategoryById(Long id);

    public Category addCategory(Category category);

    public Category updateCategory(Long categoryId, Category category);

}
