package com.ecommerce.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.model.User;
import com.ecommerce.service.UserService;



@Controller
@RequestMapping("/admin/user")
public class AdminUserController
{
    
    final private UserService userService;

    public AdminUserController(UserService userService)
    {
        this.userService = userService;
    }
    
    @GetMapping("/users")
    public String getAllUsers(Model model)
    {

        List<User> allUsers = userService.getAllUser();
        model.addAttribute("users",allUsers);
        
        return "admin/users";
    }

    @GetMapping("/updateuser/{id}")
    public String updateUser(@RequestParam("id") Long id)
    {
        return "admin/updateuser";
    }
}