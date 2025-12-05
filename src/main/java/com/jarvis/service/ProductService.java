package com.jarvis.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jarvis.dto.ProductRegisterDto;
import com.jarvis.dto.ProductResponseDTO;
import com.jarvis.dto.ProductUpdateDto;

public interface ProductService 
{
	void addProduct(ProductRegisterDto productRegisterDto);
	
	ProductResponseDTO getProduct(Long productId);
	
	List<ProductResponseDTO> getAllProducts();
	
	Page<ProductResponseDTO> getProductsWithPagination(int page, int size);
	
	void updateProduct(Long productId, ProductUpdateDto productUpdateDto);
	
	void deleteProduct(Long productId);
	
	
	
}
