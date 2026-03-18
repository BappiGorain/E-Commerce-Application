package com.ecommerce.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.dto.OrderSummaryDTO;
import com.ecommerce.model.Address;
import com.ecommerce.service.AddressService;
import com.ecommerce.service.OrderService;
import com.ecommerce.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/address")
public class AddressController {

    Logger logger = LoggerFactory.getLogger(AddressController.class);

    private final AddressService addressService;
    private final OrderService orderService;
    private final UserService userService;

    public AddressController(AddressService addressService, OrderService orderService, UserService userService)
    {
        this.addressService = addressService;
        this.orderService = orderService;
        this.userService = userService;

    }

    @GetMapping("/showAddress")
    public String showAddress(Model model,Authentication authentication)
    {

        String email = authentication.getName();

        List<Address> allAddress = addressService.getAllAddressFromEmail(email);
        model.addAttribute("addresses", allAddress);

        logger.info("All addresses are loading");
        return "user/showaddress";
    }

    @GetMapping("/addAddress")
    public String showAddressPage(Model model) {
        logger.info("Loading address page");

        model.addAttribute("address", new Address());

        return "user/address";
    }

    @PostMapping("/addAddress")
    public String addAddress(@ModelAttribute Address address, Authentication authentication) {

        String email = authentication.getName();

        addressService.addAddress(address, email);

        return "redirect:/address/showAddress";
    }

    @PostMapping("/delete/{id}")
    public String deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return "redirect:/address/showAddress";
    }

    @GetMapping("/ordersummary")
    public String orderSummary(@RequestParam Long addressId,
            Authentication authentication,
            Model model) {
        String email = authentication.getName();

        var user = userService.getUserByEmail(email);

        OrderSummaryDTO summary = orderService.getOrderSummary(user.getId(), addressId);

        model.addAttribute("address", summary.getAddress());
        model.addAttribute("cartItems", summary.getCartItems());
        model.addAttribute("totalPrice", summary.getTotalPrice());

        return "user/order-summary";
    }

}
