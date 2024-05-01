package com.example.FORMANTO.repository;

import com.example.FORMANTO.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<List<Product>> findByProductGroupId(Long productGroupId);
}
