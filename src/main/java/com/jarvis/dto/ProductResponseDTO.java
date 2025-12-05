package com.jarvis.dto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.jarvis.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO 
{
	private Long productId;
	
	private String productName;
	
	private Double productPrice;
	
	private Integer quantity;
	
	
	public static ProductResponseDTO fromEntity(Product product)
	{
		return new ProductResponseDTO(product.getProductId(),product.getProductName(),product.getProductPrice(),product.getQuantity());
	}
	
	
	public static List<ProductResponseDTO> fromEntityList(List<Product> products)
	{
		return products.stream()
				.map(ProductResponseDTO :: fromEntity)
				.toList();
	}
	
	
	public static Page<ProductResponseDTO> fromEntityPage(Page<Product> productPage)
	{
		List<ProductResponseDTO> data = productPage.getContent()
															.stream()
															.map(ProductResponseDTO ::fromEntity)
															.toList();
		
		return new PageImpl<>(data, productPage.getPageable(),productPage.getTotalElements());
	}
}
