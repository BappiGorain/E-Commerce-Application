package com.ecommerce.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    private String brand;

    @Column(name = "unit_left")
    private Integer unitLeft;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "manufacture_date")
    private LocalDate manufactureDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    private String image = "Default.png";

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}
