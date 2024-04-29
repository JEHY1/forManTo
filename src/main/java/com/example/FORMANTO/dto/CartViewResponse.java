package com.example.FORMANTO.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartViewResponse {

    private int count;
    private String productName;
    private String company;
    private int price;

    @Builder
    public CartViewResponse(String productName, String company, int price, int count){
        this.productName = productName;
        this.company = company;
        this.price = price;
        this.count = count;
    }
}
