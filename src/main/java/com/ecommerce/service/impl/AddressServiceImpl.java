package com.ecommerce.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.model.Address;
import com.ecommerce.model.User;
import com.ecommerce.repository.AddressRepo;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService
{

    private final AddressRepo addressRepo;
    private final UserRepository userRepo;

    public AddressServiceImpl(AddressRepo addressRepo, UserRepository userRepo)
    {
        this.addressRepo = addressRepo;
        this.userRepo = userRepo;
    }
    
    @Override
    public Address addAddress(Address address,String email)
    {
        
        User user = userRepo.findByEmail(email).get();
        address.setUser(user);
        Address savedAddress = addressRepo.save(address);
        return savedAddress;
    }

    @Override
    public List<Address> getAllAddress()
    {
        return addressRepo.findAll();    
    }

    @Override
    public void deleteAddress(Long id)
    {
        addressRepo.deleteById(id);
    }
    
}
