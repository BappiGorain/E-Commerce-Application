package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.OrderSummaryDTO;
import com.ecommerce.model.Order;

public interface  OrderService
{
    public OrderSummaryDTO getOrderSummary(Long userId, Long addressId);
    public Order placeOrder(Long userId, Long addressId);
    List<Order> getUserOrders(Long userId);
}
