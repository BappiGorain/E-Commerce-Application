// package com.ecommerce.model;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;
// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Entity
// @Table(name = "users")
// public class User
// {   
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private String id;

//     private String name;

//     @Column(unique = true)
//     private String email;

//     private String password;

//     private String role;

//     private boolean enable = true;

// }
