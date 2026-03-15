package com.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.model.CartItem;
import com.ecommerce.model.Order;
import com.ecommerce.model.OrderItem;
import com.ecommerce.repository.OrderItemRepo;
import com.ecommerce.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService
{

    private final OrderItemRepo orderItemRepo;

    OrderItemServiceImpl(OrderItemRepo orderItemRepo) {
        this.orderItemRepo = orderItemRepo;
    }
    
    
    
    @Override
    public void convertCartItemsToOrderItems(List<CartItem> cartItems,Order order)
    {
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cartItems)
        {
            OrderItem item = new OrderItem();

            item.setOrder(order);
            item.setProduct(cartItem.getProduct());
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(cartItem.getProduct().getPrice());

            orderItems.add(item);
        }

        orderItemRepo.saveAll(orderItems);
    }



    @Override
    public List<OrderItem> getAllOrderItem()
    {
        List<OrderItem> orderItems = orderItemRepo.findAll();
        return orderItems;
    }



    
}
