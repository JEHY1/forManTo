package com.example.FORMANTO.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserViewController {

    @GetMapping("/userQnA")
    public String userQnA(Principal principal){
        return "user/QnA";
    }

    @GetMapping("/cart")
    public String cart(Principal principal){
        return "/user/cart";
    }

    @GetMapping("/review")
    public String review(Principal principal){
        return "/user/review";
    }
}
