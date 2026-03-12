package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.OrderItem;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem,Long>
{
    
}
