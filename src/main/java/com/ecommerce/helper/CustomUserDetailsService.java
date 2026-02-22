// package com.ecommerce.helper;

// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;

// import com.ecommerce.model.User;
// import com.ecommerce.repository.UserRepository;

// public class CustomUserDetailsService implements UserDetailsService{

//     final private UserRepository userRepo;

//     CustomUserDetailsService(UserRepository userRepo)
//     {
//         this.userRepo = userRepo;
//     }

//     @Override
//     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
//     {
//         User user = userRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User Not found"));

//         return org.springframework.security.core.userdetails.User.
//                                                             builder()
//                                                             .username(user.getEmail())
//                                                             .password(user.getPassword())
//                                                             .roles(user.getRole().replace("ROLE_", ""))
//                                                             .build();

        
//     }
    
// }
