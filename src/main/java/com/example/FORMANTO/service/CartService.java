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

    public List<Cart> findByCustomerId(Long customerId){ //repository 를 이용한 검색결과를 추출(서비스 레이어로 뺌) Optional -> entity(또는 List<Entity>) 검색 결과가 없을 경우 exeption을 발생, 아니면 List를 리턴, 모든 서비스에서 findBy... 은 동일
        return cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new IllegalArgumentException("not found cart"));
    }
}
