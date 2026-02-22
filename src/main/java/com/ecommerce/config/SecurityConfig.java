// package com.ecommerce.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import com.ecommerce.helper.CustomUserDetailsService;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig 
// {

    
    
//     private AuthFailureHandler authFailureHandler;    
//     final private CustomUserDetailsService customUserDetailsService;

//     SecurityConfig(CustomUserDetailsService customUserDetailsService,AuthFailureHandler authFailureHandler)
//     {
//         this.customUserDetailsService = customUserDetailsService;
//         this.authFailureHandler = authFailureHandler;
//     }

//     public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
//     {
//         httpSecurity
//                     .authorizeHttpRequests(authorize->{
//                         authorize.requestMatchers("/dashboard","/register","/login","/css/**").permitAll();
//                         authorize.requestMatchers("/admin/**").hasRole("ADMIN");
//                         authorize.requestMatchers("/user/**").hasRole("USER");
//                         authorize.anyRequest().authenticated();
                    
//     });

//     httpSecurity.formLogin(formLogin->{
//         formLogin.loginPage("/login");
//         formLogin.loginProcessingUrl("/doLogin");
//         formLogin.defaultSuccessUrl("user/dashboard",true);
//         formLogin.usernameParameter("email");
//         formLogin.passwordParameter("password");

//         formLogin.failureHandler(authFailureHandler);
//     });


//     httpSecurity.csrf(AbstractHttpConfigurer::disable);
//     httpSecurity.logout(logoutForm->{
//         logoutForm.logoutUrl("/doLogin");
//         logoutForm.logoutSuccessUrl("/login?logout=true");
//     });        
//         return httpSecurity.build();
//     }


//     @Bean
//     public PasswordEncoder passwordEncoder()
//     {
//         return new BCryptPasswordEncoder();
//     }
    
    
// }
