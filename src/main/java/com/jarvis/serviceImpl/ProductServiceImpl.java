package com.jarvis.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.jarvis.dto.ProductRegisterDto;
import com.jarvis.dto.ProductResponseDTO;
import com.jarvis.dto.ProductUpdateDto;
import com.jarvis.entity.Product;
import com.jarvis.exception.ProductNameAlreadyExistsExeception;
import com.jarvis.exception.ProductNotFoundException;
import com.jarvis.repository.ProductRepository;
import com.jarvis.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService
{
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void addProduct(ProductRegisterDto productRegisterDto) 
	{
		Optional<Product> exists = productRepository.findByProductName(productRegisterDto.getProductName());
		if (exists.isPresent())
		{
			throw new ProductNameAlreadyExistsExeception("Product Name Already exists");
		}
		
		Product product = new Product();
		product.setProductName(productRegisterDto.getProductName());
		product.setProductPrice(productRegisterDto.getProductPrice());
		product.setQuantity(productRegisterDto.getQuantity());
		productRepository.save(product);	
	}
	
	@Override
	public ProductResponseDTO getProduct(Long productId) 
	{
		Product product = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("Product is not found"));
		return ProductResponseDTO.fromEntity(product);
	}

	@Override
	public List<ProductResponseDTO> getAllProducts() 
	{
		List<Product> result = productRepository.findAll();
		List<ProductResponseDTO> data = ProductResponseDTO.fromEntityList(result);
		return data;
	}

	@Override
	public Page<ProductResponseDTO> getProductsWithPagination(int page, int size) 
	{
		Pageable pageable = PageRequest.of(page, size,Sort.by("productId").ascending());
		Page<Product> result = productRepository.findAll(pageable);
		return ProductResponseDTO.fromEntityPage(result);	
	}
	
	  @Override
	    public void updateProduct(Long productId, ProductUpdateDto updateDto) 
	  {
	        Product product = productRepository.findById(productId)
	                							.orElseThrow(() -> new ProductNotFoundException("Product not found"));

	        if (updateDto.getProductPrice() != null) 
	        {
	            product.setProductPrice(updateDto.getProductPrice());
	        }
	        if (updateDto.getQuantity() != null)
	        {
	            product.setQuantity(updateDto.getQuantity());
	        }
	        
	        productRepository.save(product);
	    }

	@Override
	public void deleteProduct(Long productId) 
	{
		Product product = productRepository.findById(productId)
	            						   .orElseThrow(() -> new ProductNotFoundException("Product not found"));
	    productRepository.delete(product);
	}

	

	

}
