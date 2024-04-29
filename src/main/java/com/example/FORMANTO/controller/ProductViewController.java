package com.example.FORMANTO.controller;

import com.example.FORMANTO.service.QnAService;
import com.example.FORMANTO.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ProductViewController {

    private final ReviewService reviewService;
    private final QnAService qnAService;

    @GetMapping("/productDetail/{id}")
    public String productDetail(@PathVariable long id, Model model){

        //
        model.addAttribute("reviews", reviewService.productReviews(id));
        model.addAttribute("QnAs", qnAService.productQnA(id));

        return "product/productDetail";
    }

    @GetMapping("/main")
    public String home(){
        return "product/main";
    }
}
