package com.example.FORMANTO.service;

import com.example.FORMANTO.domain.ProductGroup;
import com.example.FORMANTO.domain.Wish;
import com.example.FORMANTO.dto.WishResponse;
import com.example.FORMANTO.repository.ProductGroupRepository;
import com.example.FORMANTO.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WishListService {

    private final WishRepository wishRepository;
    private final ProductGroupRepository productGroupRepository;

    //optional이 아닌 list형식으로 바꾸기
    public List<Wish> findByCustomerId(Long customerId) {
        return wishRepository.findAllByCustomerCustomerId(customerId)
                .orElseThrow(() -> new IllegalArgumentException("not found wish"));
        //List형식이 아닌 형식은 어떻게 만들 수 있을까?
    }

    public ProductGroup findByProductGroupId(Long productGroupId) {
        //findById는 Jpa도 그냥 제공하는 기능인가봄
        return productGroupRepository.findById(productGroupId)
                .orElseThrow(()-> new IllegalArgumentException("not found ProductGroup"));
    }

    public List<ProductGroup> findAllByProductIdId(Long productGorupId) {
        return productGroupRepository.findAllByProductGroupId(productGorupId)
                .orElseThrow(() -> new IllegalArgumentException("not found ProductGroup"));
    }

    public List<Wish> findAllByCustomerId(Long customer) {
        return wishRepository.findAllByCustomerCustomerId(customer)
                .orElseThrow(()->new IllegalArgumentException("not found wish"));
    }





    //db-> view로 데이터 넘기기
    public List<WishResponse> wishview(Long customerId) {
        //1. customerId를 받아와서 wish의 리파지토리 테이블 레코드를 받아야함
//      List<Wish> wishes = findByCustomerId(customerId);
        List<Wish> wishes = findAllByCustomerId(customerId);
        //2. 받은 레코드의 productGroupID를 추출해서 productGroupTb를 접근
        //  3. productGroupTb안에 잇는 company, name, price    img도 있어야 한데 어디에서 가져와야 하는지 잘 모르겠네


        List<WishResponse> wishResponses = new ArrayList<>();
        wishes.forEach(wish -> {


            //@ManytoOner으로 Join하기
//            log.info(wish.getProductGroup().getProductGroupId().toString());


            ProductGroup productGroups = findByProductGroupId(wish.getProductGroup().getProductGroupId());
            wishResponses.add(WishResponse.builder()
                    .src("")
                    .company(productGroups.getCompany())
                    .price(productGroups.getPrice())
                    .name(productGroups.getName())
                    .build());
        });
        return wishResponses;

    }

}