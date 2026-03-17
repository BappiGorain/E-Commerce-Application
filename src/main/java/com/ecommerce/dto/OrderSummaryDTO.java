package com.ecommerce.dto;

import java.util.List;

import com.ecommerce.model.Address;
import com.ecommerce.model.CartItem;

public class OrderSummaryDTO {

    private Address address;
    private List<CartItem> cartItems;
    private double totalPrice;

    public OrderSummaryDTO(Address address, List<CartItem> cartItems, double totalPrice) {
        this.address = address;
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }

    public Address getAddress() {
        return address;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}