package com.jarvis.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jarvis.entity.Product;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long>
{
	Optional<Product> findByProductName(String productName);
	
	Page<Product> findAll(Pageable pageable);
}
