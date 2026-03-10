package com.ecommerce.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.model.Address;
import com.ecommerce.service.AddressService;

import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/address")
public class AddressController
{

    Logger logger = LoggerFactory.getLogger(AddressController.class);

    final private AddressService addressService;

    public AddressController(AddressService addressService)
    {
        this.addressService = addressService;
    }


    @GetMapping("/showAddress")
    public String showAddress(Model model)
    {

        List<Address> allAddress = addressService.getAllAddress();
        model.addAttribute("addressess", allAddress);
        
        
        logger.info("All addresses are loading");
        return "user/showaddress";
    }
    
    @GetMapping("/addAddress")
    public String showAddressPage(Model model)
    {
        logger.info("Loading address page");

        model.addAttribute("address", new Address());

        return "user/address";
    }
    

    @PostMapping("/addAddress")
    public String addAddress(@ModelAttribute Address address,Authentication authentication)
    {


        String email = authentication.getName();
        
        addressService.addAddress(address,email);

        return "redirect:/address/showAddress";
    }
    
    
    
}
