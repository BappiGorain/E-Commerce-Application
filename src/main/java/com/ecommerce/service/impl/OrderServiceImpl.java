package com.ecommerce.service.impl;

import org.springframework.stereotype.Service;

import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Address;
import com.ecommerce.repository.AddressRepo;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService
{


    private final UserRepository userRepo;
    private final AddressRepo addressRepo;

    public OrderServiceImpl(UserRepository userRepo,AddressRepo addressRepo)
    {
        this.userRepo = userRepo;
        this.addressRepo = addressRepo;
    }
    
    
    
    
    @Override
    public void getOrderSummary(Long userId, Long addressId)
    {
        

    }

    
    
}
