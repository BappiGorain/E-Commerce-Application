package com.ecommerce.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ecommerce.model.User;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.CartService;


@ControllerAdvice
public class GlobalModelAttributes
{
    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute
    public void addCartCount(Model model,
                             @AuthenticationPrincipal UserDetails userDetails)
    {
        if(userDetails != null)
        {
            User user = userRepository.findByEmail(userDetails.getUsername()).get();

            int cartCount = cartService.getCartItemCount(user);

            model.addAttribute("cartCount", cartCount);
            model.addAttribute("user",user);
        }
    }
}