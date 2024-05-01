package com.example.FORMANTO.service;

import com.example.FORMANTO.domain.Product;
import com.example.FORMANTO.domain.ProductGroup;
import com.example.FORMANTO.repository.ProductGroupRepository;
import com.example.FORMANTO.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductGroupRepository productGroupRepository;
    public ProductGroup findProductGroupByProductGroupId(Long productGroupId){
        return productGroupRepository.findById(productGroupId)
                .orElseThrow(() -> new IllegalArgumentException("not found productGroup"));
    }

    public List<Product> findProductByProductGroupId(Long productGroupId){
        return productRepository.findByProductGroupId(productGroupId)
                .orElseThrow(() -> new IllegalArgumentException("not found product"));
    }


    public Product findProductByProductId(Long productId){
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("not found product"));
    }
}
