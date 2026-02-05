package com.ecommerce.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonFormat;


@Data
public class ProductRequest
{
    @NotBlank
    private String name;

    @NotNull
    @Positive
    private Double price;

    private Integer unitLeft;

    @JsonFormat(pattern = "dd-mm-yyyy")
    private LocalDate manufactureDate;

    @JsonFormat(pattern = "dd-mm-yyyy")
    private LocalDate expirationDate;
}
