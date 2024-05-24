package com.example.FORMANTO.dto;
//db-> view로 데이터 뿌리는 dto

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@ToString
public class WishResponse {


    private String company;
    private String name;
    private int price;
    private String src;

    @Builder
    public WishResponse(String company, String name, int price, String src) {
        this.company = company;
        this.name = name;
        this.price = price;
        this.src = src;
    }



}