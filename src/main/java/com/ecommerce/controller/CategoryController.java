package com.ecommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.model.Category;
import com.ecommerce.service.CategoryService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
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

    @GetMapping("/addCategory")
    public String showAddCategoryPage(Model model) 
    {
        logger.info("Add new Category");   
        model.addAttribute("category",new Category());
        return "addCategory";
    }


    @PostMapping("/addCategory")
    public String addCategory(@ModelAttribute("category") Category category)
    {
        Category addedCategory = categoryService.addCategory(category);
        logger.info("New Category added with id : " + addedCategory.getId() + " and name is : " + addedCategory.getName());
        return "redirect:/category/addCategory";
    }

    @GetMapping("/allCategories")
    public String getAllCategies(Model model)
    {
        logger.info("All Categories returned");
        List<Category> allCategories = categoryService.getAllCategories();
        model.addAttribute("categories",allCategories);
        return "allcategories";
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id)
    {
        logger.info("Category fetched with id : " + id);
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/deleteCategory")
    public String showDeleteCategoryPage()
    {
        logger.info("Delete Category loadded");
        return "deletecategory";
    }

    @PostMapping("/delete")
    public String deleteCateory(@RequestParam Long id)
    {
        categoryService.deleteCategoryById(id);
        logger.info("Category deleted with id " + id);
        return "redirect:/category/allCategories";
    }

    @GetMapping("/updateCategory")
    public String showUpdateCategoryPag(Model model) 
    {
        logger.info("update category page is loading");
        model.addAttribute("category",new Category());
        return "updatecategory";
    }
    

    @PostMapping("/updateCategory")
    public String updateCategory(@ModelAttribute Category category)
    {
        Category updatedCategory = categoryService.updateCategory(category.getId(), category);
        logger.info("Category updated to " + updatedCategory.getName());
        return "redirect:/category/allCategories";
    }
}