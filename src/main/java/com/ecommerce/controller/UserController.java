package com.ecommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController
{

    Logger logger = LoggerFactory.getLogger(UserController.class);
    
    
    @GetMapping("/dashboard")
    public String showDashboard()
    {
        logger.info("Dashboard is loaded");
        return "dashboard";
    }
        
}
