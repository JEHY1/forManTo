package com.example.FORMANTO.controller;


import com.example.FORMANTO.domain.Cart;
import com.example.FORMANTO.domain.Product;
import com.example.FORMANTO.domain.Review;

import com.example.FORMANTO.dto.cartSelectChangeResponse;
import com.example.FORMANTO.dto.user.CartViewResponse;
import com.example.FORMANTO.dto.user.review.ReviewViewRequest;
import com.example.FORMANTO.service.*;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Controller
@RequiredArgsConstructor
@Slf4j
public class UserViewController {

    private final CartService cartService;
    private final ProductService productService;
    private final ReviewService reviewService;
    private final WishListService wishListService;
    private final QnAMypageService qnAMypageService;


    @GetMapping("userQnA")
    public String userQnA(Principal principal, Model model){

        model.addAttribute("views",qnAMypageService.myPageQnaView(1L));

        return "/user/QnA";
    }

    @PostMapping("/serchUserQnA")
    public String serchUserQnA() {



        return "redirect:/userQnA";
    }


    //db에서 데이터 가져와서 view에 뿌리기
    @GetMapping("/cart")
    public String cart(Principal principal, Model model) {

        //엔티티->디티오
        List<Cart> carts = cartService.findByCustomerId(37L);//유저1인사람 리퍼지스토리 조회
        List<CartViewResponse> cartView = new ArrayList<>();
        carts.forEach(cart -> {//엔티티
            Product product = productService.findProductByProductId(cart.getProductId());
            cartView.add(CartViewResponse.builder()
                    .cartId(cart.getCartId())
                    .productId(product.getProductId())
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
        model.addAttribute("customerId", 37L);

        return "/user/cart";
    }



    //장바구니 셀랙트 클릭시 db에 변환된 가격 넘기기
    @PostMapping("/selectUpdate")                       //장바구니의 모든 데이터가 넘어옴
    public String cartSeletChange(@RequestBody cartSelectChangeResponse response, Model model){
        //서비스에게 디비 요청
        //cart db에게 proudctId에 맞는 제품의 count를 변경

        System.err.println("called");

        //dto데이터 들어왔는지 확인
        System.err.println("getProductId : " + response.getProductId());
        System.err.println("getCount : " + response.getCount());
        System.err.println("getCustomerId : " + response.getCustomerId());
        System.err.println("getCountOri : " + response.getCountOri());


        //된다면 단일로 최적화
        //모든 데이터 중 하나의 데이터가 바꼈는지 확인하는 과정  (1-1.모든 데이터를 리스트로 받는다는 가정)
        for (int i = 0; i < response.getCountOri().size(); i++) {

            if (response.getCountOri().get(i).intValue() != response.getCount().get(i).intValue()) { //바뀐 인덱스만 엔티티로 넘기기
                cartService.updateCount(response.getCustomerId().get(i)
                        , response.getProductId().get(i)
                        , response.getCount().get(i));
            }
        }



        //리다렉트 해서 다시 cart 페이지 요청
        return "redirect:/cart";
    }





    //    디티오->엔티티  리뷰작성완료시 디비에 저장
    @PostMapping("/review")
    public String reviewSend(@ModelAttribute ReviewViewRequest request) {
        log.info("on review (post)");
        Review review = reviewService.save(request);
        System.err.println("after save response : " + review.toString());


        return "redirect:/review";

    }



    //db에서 데이터 가져와서 view에 데이터 뿌리기
    @GetMapping("/review")
    public String review(Principal principal, Model model){

        log.info("on review(Get)");
//        System.err.println(reviewService.findById(3L));//db->읽어오기


        model.addAttribute("views",reviewService.mypageReview(37L));
        System.err.println("reviewService mypageReview : "+reviewService.mypageReview(37L));

//        System.err.println(reviewService.productReviews(1L));

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
    public String wishlist(Principal principal, Model model){
        log.info("on wishlist(Get)");
        model.addAttribute("wishviews",wishListService.wishview(37L));
        //System.err.println("wishlistview  : "+wishListService.wishview(37L));


        return "/user/wishlist";
    }

    @GetMapping("/orderCancel")//날짜 입력시 주문날짜와 수량 상품이름 출력
    public String orderCancel(){
        return "/user/orderCancel";
    }

    @GetMapping("/orderInquiry")
    public String orderInquiry() {
        return "/user/orderInquiry";
    }


}