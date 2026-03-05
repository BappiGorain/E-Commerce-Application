package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.model.Cart;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.User;
import com.ecommerce.repository.CartItemRepo;
import com.ecommerce.repository.CartRepo;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.CartService;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepo cartRepository;

    @Autowired
    private CartItemRepo cartItemRepository;


    /*
     * ===============================
     * ADD PRODUCT TO CART
     * ===============================
     */
    @GetMapping("/cart/add/{productId}")
    public String addToCart(@PathVariable Long productId,
                            @AuthenticationPrincipal UserDetails userDetails) {

        String email = userDetails.getUsername();

        User user = userRepository.findByEmail(email).get();

        cartService.addToCart(productId, user);

        return "redirect:/user/cart";
    }


    /*
     * ===============================
     * VIEW USER CART
     * ===============================
     */
    @GetMapping("/user/cart")
    public String viewCart(@AuthenticationPrincipal UserDetails userDetails,
                           Model model) {

        String email = userDetails.getUsername();

        User user = userRepository.findByEmail(email).get();

        Cart cart = cartRepository.findByUser(user);

        List<CartItem> items = List.of();

        double totalPrice = 0;

        if (cart != null) {
            items = cartItemRepository.findByCart(cart);
            totalPrice = cartService.calculateTotalCartPrice(cart);
        }

        model.addAttribute("cartItems", items);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("user", user);

        return "user/cart";
    }


    /*
     * ===============================
     * REMOVE ITEM FROM CART
     * ===============================
     */
    @GetMapping("/cart/remove/{id}")
    public String removeItemFromCart(@PathVariable Long id,
                                     @AuthenticationPrincipal UserDetails userDetails) {

        String email = userDetails.getUsername();

        User user = userRepository.findByEmail(email).get();

        cartService.removeItemFromCart(id, user);

        return "redirect:/user/cart";
    }

}