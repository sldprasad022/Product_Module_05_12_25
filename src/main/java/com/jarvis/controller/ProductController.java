package com.jarvis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jarvis.dto.APIResponse;
import com.jarvis.dto.ProductRegisterDto;
import com.jarvis.dto.ProductResponseDTO;
import com.jarvis.dto.ProductUpdateDto;
import com.jarvis.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/product")
@Tag(name="Product ", description = "Product module related API's")
public class ProductController 
{
	@Autowired
	private ProductService productService;
	
	@PostMapping
	@Operation(summary = "Create a new product", description = "This API is used to add a new product by passing product details such as `name, price, and quantity`.")
	public ResponseEntity<APIResponse<Void>> addProduct(@Valid @RequestBody ProductRegisterDto productRegisterDto)
	{
		productService.addProduct(productRegisterDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(APIResponse.success(HttpStatus.CREATED.value(), "Product created successfully", null));
	}
	
	@GetMapping("/{productId}")
	@Operation(summary = "Get product by ID",description = "This API returns product details based on the provided `product ID`.")
	public ResponseEntity<APIResponse<ProductResponseDTO>> getProduct(@PathVariable Long productId)
	{
		ProductResponseDTO result = productService.getProduct(productId);
		return ResponseEntity.ok(APIResponse.success(HttpStatus.OK.value(), "Product data retrieved successfully", result));
	}
	
	@GetMapping("/get-all")
	@Operation( summary = "Get all products",description = "This API returns a list of all products stored in the db.")
	public ResponseEntity<APIResponse<List<ProductResponseDTO>>> getAll()
	{
		List<ProductResponseDTO> result = productService.getAllProducts();
		return ResponseEntity.ok(APIResponse.success(HttpStatus.OK.value(), "All Products info fetched successfully", result));
	}
	
	@GetMapping("/paginated/get-all")
	@Operation( summary = "Get paginated list of products",description = "Returns paginated data of products based on `page number and page size`.")
	public ResponseEntity<APIResponse<Page<ProductResponseDTO>>> getAllPaginatedProducts(
														@RequestParam(defaultValue = "0") int page,
														@RequestParam(defaultValue = "10") int size)
	{
		Page<ProductResponseDTO> result = productService.getProductsWithPagination(page, size);
		return ResponseEntity.ok(APIResponse.success(HttpStatus.OK.value(), "Paginated Products data retrieved successfully", result));
	}
	
	@PatchMapping("/{productId}")
	@Operation(summary = "Update product details", description = "This API updates the product details such as `price and quantity`.")
    public ResponseEntity<APIResponse<Void>> updateProduct(@PathVariable Long productId,@Valid @RequestBody ProductUpdateDto productUpdateDto)       												
	{
		productService.updateProduct(productId, productUpdateDto);
        return ResponseEntity.ok(APIResponse.success(HttpStatus.OK.value(), "Product details updated successfully",null));    
    }
	
	@DeleteMapping("/{productId}")
	@Operation(summary = "Delete a product",description = "This API deletes a product from the system based on the provided `product ID`.")
	public ResponseEntity<APIResponse<Void>> deleteProduct(@PathVariable Long productId) 
	{
	    productService.deleteProduct(productId);
	    return ResponseEntity.ok(APIResponse.success(HttpStatus.OK.value(), "Product deleted successfully", null));
	}

}
