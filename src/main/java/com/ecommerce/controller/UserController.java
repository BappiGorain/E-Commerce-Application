package com.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.repository.ProductRepo;
import com.ecommerce.repository.UserRepository;



@Controller
@RequestMapping("/user")
public class UserController
{

    Logger logger = LoggerFactory.getLogger(UserController.class);


    private final UserRepository userRepository;  
    private final ProductRepo productRepo;
    
    public UserController(UserRepository userRepository,ProductRepo productRepo)
    {
        this.userRepository = userRepository;
        this.productRepo = productRepo;
    }


     @GetMapping("/user-profile")
    public String userProfile(@AuthenticationPrincipal UserDetails userDetails, Model model) 
    {

        String email = userDetails.getUsername();

        Optional<User> userOptional = userRepository.findByEmail(email);

        User user = userOptional.get();

        model.addAttribute("user", user);

        return "user/user-profile";
    }


    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails userDetails, Model model)
    {
        String email = userDetails.getUsername();

        User user = userRepository.findByEmail(email).get();

        model.addAttribute("user", user);

        return "dashboard";
    }


    @GetMapping("/products")
    public String products(@AuthenticationPrincipal UserDetails userDetails, Model model)
    {
        String email = userDetails.getUsername();

        User user = userRepository.findByEmail(email).get();
        List<Product> allProducts = productRepo.findAll();

        model.addAttribute("user", user);
        model.addAttribute("products",allProducts);

        return "user/products";
    }


    
    
    
        
}
