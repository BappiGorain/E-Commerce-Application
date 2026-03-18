package com.ecommerce.service;

import java.util.List;

import com.ecommerce.model.Address;

public interface AddressService 
{
    public Address addAddress(Address address,String email);
    public List<Address> getAllAddress();
    public void deleteAddress(Long id);
    public Address getAddressById(Long addressId);
    public List<Address> getAllAddressFromEmail(String email);
}
