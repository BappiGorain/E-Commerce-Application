package com.ecommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import com.ecommerce.model.Category;
import com.ecommerce.service.CategoryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/category")
public class CategoryController {

    Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // ================= ADD =================

    @GetMapping("/addCategory")
    public String showAddCategoryPage(Model model) {
        model.addAttribute("category", new Category());
        return "addCategory";
    }

    @PostMapping("/addCategory")
    public String addCategory(@ModelAttribute Category category) {
        categoryService.addCategory(category);
        return "redirect:/category/allCategories";
    }

    // ================= LIST =================

    @GetMapping("/allCategories")
    public String getAllCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "allcategories";
    }

    // ================= UPDATE =================

    @GetMapping("/updateCategory/{id}")
    public String showUpdateCategoryPage(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "updatecategory";
    }

    @PostMapping("/updateCategory")
    public String updateCategory(@ModelAttribute Category category) {
        categoryService.updateCategory(category.getId(), category);
        return "redirect:/category/allCategories";
    }

   // ================= DELETE =================

// STEP 1: Show confirmation page
@GetMapping("/deleteCategory/{id}")
public String showDeleteConfirmation(@PathVariable Long id, Model model) {
    model.addAttribute("category", categoryService.getCategoryById(id));
    return "deletecategory";
}

// STEP 2: Perform delete AFTER confirmation
@PostMapping("/deleteCategory/{id}")
public String deleteCategory(@PathVariable Long id) {
    categoryService.deleteCategoryById(id);
    return "redirect:/category/allCategories";
}

    
}
