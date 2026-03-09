package com.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ecommerce.service.CategoryService;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UserService;

@Controller
public class AdminController 
{

    final private UserService userService;
    final private CategoryService categoryService;
    final private ProductService productService;

    AdminController(UserService userService, CategoryService categoryService, ProductService productService
    )
    {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }


   @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model)
    {

        int userSize = userService.getAllUser().size();
        int categorySize = categoryService.getAllCategories().size();
        int productSize = productService.getAllProduct().size();

        model.addAttribute("totalUsers",userSize);
        model.addAttribute("totalCategories",categorySize);
        model.addAttribute("totalProducts",productSize);

        

        return "admin/dashboard";
    }

    
    
    
    
    
    
}