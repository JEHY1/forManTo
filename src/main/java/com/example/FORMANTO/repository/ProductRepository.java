package com.example.FORMANTO.repository;

import com.example.FORMANTO.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
