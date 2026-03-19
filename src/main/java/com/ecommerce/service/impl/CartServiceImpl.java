package com.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Cart;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.repository.CartItemRepo;
import com.ecommerce.repository.CartRepo;
import com.ecommerce.repository.ProductRepo;
import com.ecommerce.service.CartService;

@Service
public class CartServiceImpl implements CartService
{

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CartRepo cartRepository;

    @Autowired
    private CartItemRepo cartItemRepository;

    @Override
    public void addToCart(Long productId, User user) {

        Product product = productRepo.findById(productId).get();

        Cart cart = cartRepository.findByUser(user);

        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(1);
        cartItem.setTotalPrice(product.getPrice());

        cartItemRepository.save(cartItem);

    }

    public double calculateTotalCartPrice(Cart cart) 
    {
        if (cart == null)
            return 0;

        List<CartItem> items = cartItemRepository.findByCart(cart);

        double total = 0;

        for (CartItem item : items) 
        {
            total += item.getProduct().getPrice() * item.getQuantity();
        }

        return total;
    }

    @Override
    public void removeItemFromCart(Long cartItemId, User user) {
        Cart cart = cartRepository.findByUser(user);

        CartItem item = cartItemRepository.findById(cartItemId).orElse(null);

        if (item != null && item.getCart().getId().equals(cart.getId())) {
            cartItemRepository.delete(item);
        }

    }

    @Override
    public int getCartItemCount(User user) 
    {
        

        Cart cart = cartRepository.findByUser(user);
        
        if(cart == null)
            return 0;

        int count = 0;

        List<CartItem> items = cartItemRepository.findByCart(cart);

        for(CartItem item : items)
            count+=item.getQuantity();
        
        
        return count;
    }
}
