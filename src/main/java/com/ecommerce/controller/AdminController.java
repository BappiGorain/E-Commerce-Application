package com.ecommerce.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ecommerce.model.UserPrincipal;

@Controller
public class AdminController 
{

   @GetMapping("/admin/dashboard")
    public String adminDashboard(Authentication authentication, Model model)
    {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        model.addAttribute("user", principal.getUser());

        return "admin/dashboard";
    }

    
    
    
    
    
    
}