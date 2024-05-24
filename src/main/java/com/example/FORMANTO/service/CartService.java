package com.example.FORMANTO.service;

import com.example.FORMANTO.domain.Cart;
import com.example.FORMANTO.dto.productDetail.AddCartRequest;
import com.example.FORMANTO.repository.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public List<Cart> findByCustomerId(Long customerId){ //repository 를 이용한 검색결과를 추출(서비스 레이어로 뺌) Optional -> entity(또는 List<Entity>) 검색 결과가 없을 경우 exeption을 발생, 아니면 List를 리턴, 모든 서비스에서 findBy... 은 동일
        return cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new IllegalArgumentException("not found cart"));
    }

    @Transactional
    public void updateCount(Long customerId, Long productId, int changeCount){
        Cart findCart = cartRepository.findByCustomerIdAndProductId(customerId, productId)
                .orElseThrow(() -> new IllegalArgumentException("not found cart"));
        /*특정 고객이 가진 상품의 수량 변경을 위해
         *카트 정보 가저옴
         */
//        findCart.setCount(changeCount); // 장바구니 특정 상품 수량 변경

        /*
         * Cart 객체 데이터 변경 안되는 상태
         * db에 어떻게 반영?
         */


        findCart.updateSelect(changeCount);

        System.err.println("findCart: "
                + findCart.getProductId() + ", "
                + findCart.getCount() + ", "
                + findCart.getCustomerId() + ", "
                + changeCount);

    }

    @Transactional
    public List<Cart> addCart(AddCartRequest request, Long customerId){
        int index = 0;
        List<Cart> carts = new ArrayList<>();

        for(Long productId : request.getProductIds()){
            Cart cart = cartRepository.findByCustomerIdAndProductId(customerId, productId).orElse(null);
            if(cart == null){
                cartRepository.save(Cart.builder()
                        .customerId(customerId)
                        .productId(productId)
                        .count(request.getCounts().get(index++))
                        .build()
                );
            }
            else{
                cart.addSelect(request.getCounts().get(index++));
            }

        }
        return carts;
    }
}
