package com.ecommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.ecommerce.model.User;
import com.ecommerce.service.impl.UserServiceImpl;

import jakarta.validation.Valid;




@Controller
public class PageController
{
    Logger logger = LoggerFactory.getLogger(PageController.class);

    private final UserServiceImpl userServiceImpl;
   

    PageController(UserServiceImpl userServiceImpl)
    {
        this.userServiceImpl = userServiceImpl; 
    }

    @GetMapping("/register")
    public String registerPage(Model model)
    {

        model.addAttribute("user",new User());

        logger.info("Register page loading");

        return "/register";
    }


    @PostMapping("/processing-register")
    public String processingRegister(@Valid @ModelAttribute User user,BindingResult bindingResult) 
    {

        if(bindingResult.hasErrors())
        {
            logger.info("There is some error in fields");
            return "/register";
        }

        User newUser = userServiceImpl.registerNewUser(user);

        logger.info("new User added with name : " + newUser.getName());
        
        return "redirect:/login";
    }
    


    @GetMapping("/login")
    public String loginPage(Model model)
    {
        model.addAttribute("user",new User());

        logger.info("Loggin page loading");

        return "/login";
    }



   

    
}