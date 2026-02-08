package com.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;


@Controller
public class TestController
{

    final private ProductService productService;

    TestController(ProductService productService)
    {
        this.productService = productService;
    }


    @GetMapping("/product")
    public String allProducts(Model model)
    {
        System.out.println("All product");

        List<Product> allProduct = productService.getAllProduct();

        model.addAttribute("products",allProduct);

        return "products";
    }


    @GetMapping("/test")
    public String test(Model model)
    {
        
        model.addAttribute("name","Bappi");



        ArrayList<String> animals = new ArrayList<>();
        animals.add("Tiger");
        animals.add("Elephant");
        animals.add("Cat");
        animals.add("Dog");
        animals.add("cheath");

        model.addAttribute("animalList",animals);
        
        
        
        
        
        return "test";
    }
    
}
