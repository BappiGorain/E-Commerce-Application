package com.ecommerce.service;

import com.ecommerce.dto.OrderSummaryDTO;

public interface  OrderService
{
    public OrderSummaryDTO getOrderSummary(Long userId, Long addressId);
}
