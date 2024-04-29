package com.example.FORMANTO.controller;


import com.example.FORMANTO.domain.Cart;
import com.example.FORMANTO.domain.Product;
import com.example.FORMANTO.dto.CartViewResponse;
import com.example.FORMANTO.service.CartService;


import com.example.FORMANTO.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class UserViewController {

    private final CartService cartService;
    private final ProductService productService;

    @GetMapping("/userQnA")
    public String userQnA(Principal principal){
        return "user/QnA";
    }

    @GetMapping("/cart")
    public String cart(Principal principal, Model model){

        List<Cart> carts = cartService.findByCustomerId(1L);
        List<CartViewResponse> cartView = new ArrayList<>();
        carts.forEach(cart -> {
            Product product = productService.findByProductId(cart.getProductId());
            cartView.add(CartViewResponse.builder()
                    .productName(product.getName())
                    .company("test")
                    .price(product.getPrice())
                    .count(cart.getCount())
                    .build());
        });

        model.addAttribute("carts", cartView);

        return "/user/cart";
    }

    @GetMapping("/review")
    public String review(Principal principal){
        return "/user/review";
    }
}
