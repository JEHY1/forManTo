package com.example.FORMANTO.service;

import com.example.FORMANTO.domain.Review;
import com.example.FORMANTO.domain.ReviewImg;
import com.example.FORMANTO.dto.ReviewViewRequest;
import com.example.FORMANTO.dto.ReviewViewResponse;
import com.example.FORMANTO.repository.ReviewImgRepository;
import com.example.FORMANTO.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewImgRepository reviewImgRepository;

    public List<Review> findByProductGroupId(Long productGroupId){
        return reviewRepository.findByProductGroupId(productGroupId)
                .orElseThrow(() -> new IllegalArgumentException("not found review"));
    }

    public List<ReviewViewResponse> productReviews(Long productId){
        List<Review> reviews = findByProductGroupId(productId);
        List<ReviewViewResponse> reviewResponses = new ArrayList<>();
        reviews.forEach(review -> {
            List<String> reviewImgs = new ArrayList<>();
            List<ReviewImg> imgs = reviewImgRepository.findByReviewId(review.getReviewId())
                    .orElseThrow(() -> new IllegalArgumentException("not found img"));
            imgs.forEach(img -> {
                reviewImgs.add(img.getReviewImgSrc());
            });
            reviewResponses.add(ReviewViewResponse.builder()
                    .username(review.getUsername())
                    .content(review.getContent())
                    .imgs(reviewImgs)
                    .build());
        });

        return reviewResponses;
    }

    public Review save(ReviewViewRequest request){

        Review review = Review.builder()
                .username("user1")
                .productGroupId(1L)
                .content(request.getContent())
                .build();

        Review saved = reviewRepository.save(review);

        return saved;
    }

    public Review findById(Long id){//db->아이디 읽어서 고객이 리뷰쓸 제품 찾기
        return reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
    }






}
