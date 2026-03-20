package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.helper.OrderStatus;
import com.ecommerce.service.OrderService;

@Controller
@RequestMapping("/admin")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public String getAllOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "admin/orders";
    }

    @PostMapping("/orders/updateStatus")
    public String updateOrderStatus(@RequestParam Long orderId,
            @RequestParam String status) 
    {

        OrderStatus orderStatus;

        try
        {
            orderStatus = OrderStatus.valueOf(status.toUpperCase());
        } 
        catch (IllegalArgumentException e) 
        {
            throw new RuntimeException("Invalid order status: " + status);
        }

        orderService.updateOrderStatus(orderId, orderStatus);

        return "redirect:/admin/orders";
    }

}
