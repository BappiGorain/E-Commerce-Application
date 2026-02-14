package com.ecommerce.controller;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;



@Controller
public class TestController
{

    @GetMapping("/test")
    public String test(@ModelAttribute("categoryName") String name ,Model model)
    {

        System.out.println("Test is running");
        
        model.addAttribute("name","Bappi");

        ArrayList<String> animals = new ArrayList<>();
        animals.add("Tiger");
        animals.add("Elephant");
        animals.add("Cat");
        animals.add("Dog");
        animals.add("cheath");

        model.addAttribute("animalList",animals);

        System.out.println("Category Name is : " + name);
        
        return "test";
    }
}
