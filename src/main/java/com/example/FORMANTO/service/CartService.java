package com.example.FORMANTO.service;

import com.example.FORMANTO.domain.Cart;
import com.example.FORMANTO.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public List<Cart> findByCustomerId(Long customerId){
        return cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new IllegalArgumentException("not found cart"));
    }
}
