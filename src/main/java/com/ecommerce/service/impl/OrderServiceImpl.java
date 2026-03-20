package com.ecommerce.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.ecommerce.dto.OrderSummaryDTO;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.helper.OrderStatus;
import com.ecommerce.model.Address;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.Order;
import com.ecommerce.model.OrderItem;
import com.ecommerce.model.User;
import com.ecommerce.repository.AddressRepo;
import com.ecommerce.repository.CartItemRepo;
import com.ecommerce.repository.OrderItemRepo;
import com.ecommerce.repository.OrderRepo;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.OrderService;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepo;
    private final AddressRepo addressRepo;
    private final CartItemRepo cartItemRepo;
    private final OrderRepo orderRepo;
    private final OrderItemRepo orderItemRepo;

    public OrderServiceImpl(
            UserRepository userRepo,
            AddressRepo addressRepo,
            CartItemRepo cartItemRepo,
            OrderRepo orderRepo,
            OrderItemRepo orderItemRepo) {

        this.userRepo = userRepo;
        this.addressRepo = addressRepo;
        this.cartItemRepo = cartItemRepo;
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
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

    @Transactional
    @Override
    public Order placeOrder(Long userId, Long addressId) {

        var user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Address address = addressRepo.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        if (!address.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Address does not belong to user");
        }

        List<CartItem> cartItems = cartItemRepo.findByCartUserId(userId);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(user);
        order.setAddress(address);
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());

        double total = 0;

        List<OrderItem> orderItems = cartItems.stream().map(cartItem -> {

            OrderItem item = new OrderItem();

            item.setOrder(order);
            item.setProduct(cartItem.getProduct());
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(cartItem.getProduct().getPrice());

            return item;

        }).toList();

        total = orderItems.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();

        order.setTotalPrice(total);
        order.setItems(orderItems);

        Order savedOrder = orderRepo.save(order);

        cartItemRepo.deleteAll(cartItems);

        return savedOrder;
    }

    @Override
    public List<Order> getUserOrders(Long userId) 
    {

        return orderRepo.findByUserId(userId);
    }

    @Override
    @Transactional
    public void cancelOrder(Long orderId,Long userId)
    {

        User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user not found with id " + userId));

        Order order = orderRepo.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Order not found with id " + orderId));

        if(order.getStatus() != OrderStatus.CREATED)
        {
            throw new RuntimeException("Order cannot be cancelled");
        }
        
        order.setStatus(OrderStatus.CANCELLED);
        orderRepo.save(order);
    }

    @Override
    public List<Order> getAllOrders() 
    {
        return orderRepo.findAll();
    }

    @Override
    public void updateOrderStatus(Long orderId, OrderStatus status) 
    {
        Order order = orderRepo.findById(orderId)
            .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        order.setStatus(status);
        orderRepo.save(order);
    }
}
