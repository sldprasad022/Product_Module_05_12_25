package com.jarvis.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRegisterDto 
{
	@NotBlank(message = "Product name cannot be null or empty")
    @Size(min = 3, message = "Product name must be at least 3 characters long")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Product name must contain alphabets only")
    private String productName;

    @NotNull(message = "Product price cannot be null")
    @Positive(message = "Product price must be greater than 0")
    private Double productPrice;

    @NotNull(message = "Quantity cannot be null")
    @Positive(message = "Quantity must be a valid number and cannot be negative")
    private Integer quantity;
}
