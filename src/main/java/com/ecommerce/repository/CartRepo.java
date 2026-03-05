package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Cart;
import com.ecommerce.model.User;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long>
{
    Cart findByUser(User user);
}
