package com.ecommerce.service;

import com.ecommerce.model.Cart;
import com.ecommerce.model.User;

public interface CartService 
{
    void addToCart(Long productId, User user);

    double calculateTotalCartPrice(Cart cart);

    void removeItemFromCart(Long id,User user);
    
    int getCartItemCount(User user);

}

