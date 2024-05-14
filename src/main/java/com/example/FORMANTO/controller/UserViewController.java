package com.example.FORMANTO.controller;


import com.example.FORMANTO.domain.Cart;
import com.example.FORMANTO.domain.Product;
import com.example.FORMANTO.domain.Review;
import com.example.FORMANTO.dto.CartViewResponse;
import com.example.FORMANTO.dto.OrderRequest;
import com.example.FORMANTO.dto.ReviewViewRequest;
import com.example.FORMANTO.service.CartService;


import com.example.FORMANTO.service.ProductService;
import com.example.FORMANTO.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequiredArgsConstructor
@Slf4j
public class UserViewController {

    private final CartService cartService;
    private final ProductService productService;
    private final ReviewService reviewService;




    @ModelAttribute("")


    @GetMapping("/userQnA")
    public String userQnA(Principal principal){
        return "user/QnA";
    }


    @GetMapping("/cart")
    public String cart(Principal principal, Model model) {
        //엔티티->디티오
        List<Cart> carts = cartService.findByCustomerId(1L);//유저1인사람 리퍼지스토리 조회
        List<CartViewResponse> cartView = new ArrayList<>();
        carts.forEach(cart -> {//엔티티
            Product product = productService.findProductByProductId(cart.getProductId());
            cartView.add(CartViewResponse.builder()
                    .productName(product.getName())
                    .company("test")
                    .price(product.getPrice())
                    .count(cart.getCount())
                    .build());
        });

        //    public static void main(String[] args) {
//        Cart cart = new Cart(1L, 1L, 2);
//        ArrayList<Cart> list = new ArrayList<>();
//        for (Cart cart1 : list) {
//            cart1.
//        }
//    }

        model.addAttribute("carts", cartView);



        return "/user/cart";
    }

    //    디티오->엔티티  리뷰작성완료시 디비에 저장
    @PostMapping("/review")
    public String reviewSend(@ModelAttribute ReviewViewRequest request) {
        log.info("on review (post)");
        Review review = reviewService.save(request);
        System.err.println("after save response : " + review.toString());

        return "redirect:/review";

    }



    @GetMapping("/review")
    public String review(Principal principal, Model model){

        log.info("on review(Get)");
//        System.err.println(reviewService.findById(3L));//db->읽어오기

        model.addAttribute("view",reviewService.productReviews(1L));

        return "/user/review";
    }


//    public static void main(String[] args) {
//        Cart cart = new Cart(1L, 1L, 2);
//        ArrayList<Cart> list = new ArrayList<>();
//        for (Cart cart1 : list) {
//            cart1.
//        }
//    }


    @GetMapping("/wishlist")
    public String wishlist(){
        return "/user/wishlist";
    }

    @GetMapping("/orderCancel")
    public String orderCancel(){
        return "/user/orderCancel";
    }

    @GetMapping("/orderInquiry")
    public String orderInquiry() {
        return "/user/orderInquiry";
    }


}