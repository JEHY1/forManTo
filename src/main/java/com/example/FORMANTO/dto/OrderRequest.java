package com.example.FORMANTO.dto;

import com.example.FORMANTO.controller.JoinViewController;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private List<Long> productIds;
    private List<Integer> counts;
    private int totalPrice;

}
