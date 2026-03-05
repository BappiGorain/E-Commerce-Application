package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Cart;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.Product;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem,Long>
{
    List<CartItem> findByCart(Cart cart);

    CartItem findByCartAndProduct(Cart cart, Product product);
}
