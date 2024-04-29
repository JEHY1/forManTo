package com.example.FORMANTO.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ProductViewController {


    @GetMapping("/productDetail/{id}")
    public String productDetail(@PathVariable long id){

        //

        return "product/productDetail";
    }

    @GetMapping("/main")
    public String home(){
        return "product/main";
    }
}
