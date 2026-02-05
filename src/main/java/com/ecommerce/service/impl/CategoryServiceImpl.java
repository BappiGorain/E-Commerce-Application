package com.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.repository.CategoryRepo;
import com.ecommerce.repository.ProductRepo;
import com.ecommerce.service.CategoryService;

public class CategoryServiceImpl implements CategoryService
{

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ProductRepo productRepo;
    

    @Override
    public List<Category> getAllCategory()
    {
        List<Category> AllCategory = categoryRepo.findAll();

        return AllCategory;
    }

    @Override
    public Category getCategoryById(Long id)
    {
        Category category = categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category Not Found with id : " + id));
        return category;
    }

    @Override
    public Category deleteCategoryById(Long id)
    {
        Category category = categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category Not Found with id : " + id));
        return category;
    }

    @Override
    public Category addCategory(Category category)
    {
        Category savedCategory = categoryRepo.save(category);
        return savedCategory;
    }

    @Override
    public Category updateCategory(Long categoryId, Category category)
    {
        Category cat = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category Not Found With id : " + categoryId));
        cat.setName(category.getName());
        Category savedCategory = categoryRepo.save(cat);
        return savedCategory;

    }

   

    
}