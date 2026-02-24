package com.ecommerce.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name must not be empty")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Price must have value")
    @DecimalMin(value = "0.0", inclusive = true, message = "Price cannot be negative")
    @Column(nullable = false)
    private Double price;

    private String brand;

    @NotNull(message = "Must have some value")
    @DecimalMin(value = "0", inclusive = true, message = "Unit Left cannot be negative")
    @Column(name = "unit_left")
    private Integer unitLeft;

    @NotNull(message = "Manufacture Date is required")
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
