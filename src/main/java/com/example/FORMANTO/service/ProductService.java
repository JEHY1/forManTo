package com.example.FORMANTO.service;

import com.example.FORMANTO.domain.Product;
import com.example.FORMANTO.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product findByProductId(Long productId){
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("not found product"));
    }
}
