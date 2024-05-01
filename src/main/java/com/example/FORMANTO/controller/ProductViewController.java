package com.example.FORMANTO.controller;

import com.example.FORMANTO.domain.Product;
import com.example.FORMANTO.domain.ProductGroup;
import com.example.FORMANTO.service.ProductService;
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
    private final ProductService productService;

    @GetMapping("/productDetail/{id}")
    public String productDetail(@PathVariable long id, Model model){
        ProductGroup productGroup = productService.findProductGroupByProductGroupId(id);
        if(productGroup != null) {
            model.addAttribute("reviews", reviewService.productReviews(id));
            model.addAttribute("QnAs", qnAService.productQnA(id));
            model.addAttribute("products", productService.findProductByProductGroupId(id));
            model.addAttribute("productGroup", productGroup);

            System.err.println("details : " + productGroup.getDetails());
        }
        return "/product/productDetail";
    }

    @GetMapping("/main")
    public String home(){
        return "mainPage";
    }
}
