package com.ecommerce.helper;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.model.User;
import com.ecommerce.model.UserPrincipal;
import com.ecommerce.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService
{

    final private UserRepository userRepo;

    public CustomUserDetailsService(UserRepository userRepo)
    {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        User user = userRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User Not found"));


        return new UserPrincipal(user);

        
    }
    
}
