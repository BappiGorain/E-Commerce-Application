package com.ecommerce.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.model.Order;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.repository.ProductRepo;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.OrderService;
import com.ecommerce.service.ProductService;

@Controller
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final ProductService productService;
    private final UserRepository userRepository;
    private final ProductRepo productRepo;
    private final OrderService orderService;

    public UserController(ProductService productService,
                          UserRepository userRepository,
                          ProductRepo productRepo,
                          OrderService orderService) {

        this.productService = productService;
        this.userRepository = userRepository;
        this.productRepo = productRepo;
        this.orderService = orderService;
    }


    /* =============================
            USER PROFILE
       ============================= */

    @GetMapping("/user-profile")
    public String userProfile(Model model, Principal principal) {

        String email = principal.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Order> orders = orderService.getUserOrders(user.getId());

        int orderCount = orders.size();

        int productCount = orders.stream()
                .flatMap(o -> o.getItems().stream())
                .mapToInt(i -> i.getQuantity())
                .sum();

        model.addAttribute("user", user);
        model.addAttribute("orders", orders);
        model.addAttribute("orderCount", orderCount);
        model.addAttribute("productCount", productCount);

        return "user/user-profile";
    }


    /* =============================
            USER DASHBOARD
       ============================= */

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }


    /* =============================
            VIEW PRODUCTS
       ============================= */

    @GetMapping("/products")
    public String products(Model model) {

        List<Product> allProducts = productRepo.findAll();

        model.addAttribute("products", allProducts);

        return "user/products";
    }


    /* =============================
            SEARCH PRODUCT
       ============================= */

    @GetMapping("/search")
    public String searchProduct(@RequestParam("keyword") String keyword, Model model) {

        List<Product> searchedProduct = productService.searchProduct(keyword);

        model.addAttribute("products", searchedProduct);
        model.addAttribute("keyword", keyword);

        return "user/products";
    }

}