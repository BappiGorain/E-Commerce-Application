package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.OrderSummaryDTO;
import com.ecommerce.model.Order;

public interface  OrderService
{
    OrderSummaryDTO getOrderSummary(Long userId, Long addressId);
    Order placeOrder(Long userId, Long addressId);
    List<Order> getUserOrders(Long userId);
    void cancelOrder(Long orderId,Long userId);
}
