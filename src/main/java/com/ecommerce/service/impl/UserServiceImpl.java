package com.ecommerce.service.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Address;
import com.ecommerce.model.User;
import com.ecommerce.repository.AddressRepo;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.UserService;

@Service
public class UserServiceImpl implements UserService
{

    private final AddressRepo addressRepo;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder, AddressRepo addressRepo)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.addressRepo = addressRepo;
    }

    public User registerNewUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        user.setEnabled(true);

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser()
    {
        return userRepository.findAll();    
    }

   
    
}