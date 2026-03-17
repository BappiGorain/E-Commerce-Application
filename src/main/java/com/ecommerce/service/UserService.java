package com.ecommerce.service;

import java.util.List;

import com.ecommerce.model.User;

public interface UserService 
{

    public User registerNewUser(User user);
    public List<User> getAllUser();
    public User getUserByEmail(String email);

    
    
    
}
