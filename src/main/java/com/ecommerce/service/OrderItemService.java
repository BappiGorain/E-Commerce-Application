package com.ecommerce.service;

import java.util.List;

import com.ecommerce.model.CartItem;
import com.ecommerce.model.Order;
import com.ecommerce.model.OrderItem;

public interface OrderItemService
{
    public void convertCartItemsToOrderItems(List<CartItem> cartItems,Order order);
    public List<OrderItem> getAllOrderItem();
}
