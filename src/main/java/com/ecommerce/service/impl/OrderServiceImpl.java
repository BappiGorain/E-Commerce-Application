package com.ecommerce.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.dto.OrderSummaryDTO;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Address;
import com.ecommerce.model.CartItem;
import com.ecommerce.repository.AddressRepo;
import com.ecommerce.repository.CartItemRepo;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepo;
    private final AddressRepo addressRepo;
    private final CartItemRepo cartItemRepo;


    public OrderServiceImpl(UserRepository userRepo, AddressRepo addressRepo,CartItemRepo cartItemRepo) {
        this.userRepo = userRepo;
        this.addressRepo = addressRepo;
        this.cartItemRepo = cartItemRepo;
    }

    @Override
    public OrderSummaryDTO getOrderSummary(Long userId, Long addressId) {

        var user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Address address = addressRepo.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        if (!address.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Address does not belong to user");
        }

        List<CartItem> cartItems = cartItemRepo.findByCartUserId(userId);

        double total = cartItems.stream()
                .mapToDouble(i -> i.getProduct().getPrice() * i.getQuantity())
                .sum();

        return new OrderSummaryDTO(address, cartItems, total);
    }

}
