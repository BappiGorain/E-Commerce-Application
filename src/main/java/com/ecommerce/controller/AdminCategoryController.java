package com.ecommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import com.ecommerce.model.Category;
import com.ecommerce.service.CategoryService;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController 
{
    
    Logger logger = LoggerFactory.getLogger(AdminCategoryController.class);

    private final CategoryService categoryService;

    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // ================= ADD =================

    @GetMapping("/addCategory")
    public String showAddCategoryPage(Model model) {
        model.addAttribute("category", new Category());
        logger.info("category add page loading");
        return "admin/addCategory";
    }

    @PostMapping("/addCategory")
    public String addCategory(@Valid @ModelAttribute Category category,
                              BindingResult result) {

        if(result.hasErrors()) {
            return "admin/addCategory";
        }

        categoryService.addCategory(category);

        logger.info("new category added");

        return "redirect:/admin/category/allCategories";
    }

    // ================= LIST =================

    @GetMapping("/allCategories")
    public String getAllCategories(Model model) {

        model.addAttribute("categories",
                categoryService.getAllCategories());

        return "admin/allcategories";
    }

    // ================= UPDATE =================

    @GetMapping("/updateCategory/{id}")
    public String showUpdateCategoryPage(@PathVariable Long id,
                                         Model model) {

        model.addAttribute("category",
                categoryService.getCategoryById(id));

        return "admin/updatecategory";
    }

    @PostMapping("/updateCategory")
    public String updateCategory(@Valid @ModelAttribute Category category,
                                 BindingResult result) {

        if(result.hasErrors()) {
            return "admin/updatecategory";
        }

        categoryService.updateCategory(category.getId(), category);

        return "redirect:/admin/category/allCategories";
    }

    // ================= DELETE =================

    @GetMapping("/deleteCategory/{id}")
    public String showDeleteConfirmation(@PathVariable Long id,
                                         Model model) {

        model.addAttribute("category",
                categoryService.getCategoryById(id));

        return "admin/deletecategory";
    }

    @PostMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable Long id) {

        categoryService.deleteCategoryById(id);

        return "redirect:/admin/category/allCategories";
    }
}