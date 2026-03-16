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
        var user = userRepo.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + userId));

    // Fetch Address
    Address address = addressRepo.findById(addressId)
            .orElseThrow(() -> new ResourceNotFoundException("Address not found with id : " + addressId));

    // Validate address belongs to user
    if (!address.getUser().getId().equals(user.getId()))
    {
        throw new RuntimeException("Address does not belong to this user");
    }

    // Later this method will:
    // 1. Fetch Cart
    // 2. Calculate total price
    // 3. Create Order Summary
        

    }

    
    
}
