package com.ecommerce.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepo;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.ProductService;



@Controller
@RequestMapping("/user")
public class UserController
{

    Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private ProductService productService;
    
    private final UserRepository userRepository;  
    private final ProductRepo productRepo;
    
    public UserController(UserRepository userRepository,ProductRepo productRepo)
    {
        this.userRepository = userRepository;
        this.productRepo = productRepo;
    }


     @GetMapping("/user-profile")
    public String userProfile() 
    {
        return "user/user-profile";
    }


    @GetMapping("/dashboard")
    public String dashboard()
    {
        return "dashboard";
    }


    @GetMapping("/products")
    public String products(Model model)
    {        
        List<Product> allProducts = productRepo.findAll();

        model.addAttribute("products",allProducts);

        return "user/products";
    }

    @GetMapping("/search")
    public String searchProduct(@RequestParam("keyword") String keyword, Model model)
    {

        List<Product> searchedProduct = productService.searchProduct(keyword);

        model.addAttribute("products", searchedProduct);
        model.addAttribute("keyword",keyword);

        return "user/products";
        
    }
    
    
    
        
}
