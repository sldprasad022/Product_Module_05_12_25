package com.jarvis.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateDto 
{
	
    @Positive(message = "Product price must be greater than 0")
    private Double productPrice;

    @Positive(message = "Quantity must be a valid number and cannot be negative")
    private Integer quantity;
}
