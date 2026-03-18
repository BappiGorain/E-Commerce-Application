package com.ecommerce.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.model.Order;
import com.ecommerce.service.OrderService;
import com.ecommerce.service.UserService;

@Controller
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;

    public OrderController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @PostMapping("/place-order")
    public String placeOrder(@RequestParam Long addressId, Principal principal) {

        Long userId = userService.getUserId(principal);

        orderService.placeOrder(userId, addressId);

        return "redirect:/order/success";
    }
   @GetMapping("/order/success")
    public String orderSuccess() {
        return "order-success";
    }

    @GetMapping("/user/orders")
    public String getUserOrders(Model model, Principal principal)
    {

    Long userId = userService.getUserId(principal);

    List<Order> orders = orderService.getUserOrders(userId);

    model.addAttribute("orders", orders);

    return "user/orders";
}
    
    
    
}