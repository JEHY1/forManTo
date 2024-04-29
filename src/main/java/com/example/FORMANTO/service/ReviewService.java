package com.example.FORMANTO.service;

import com.example.FORMANTO.domain.Review;
import com.example.FORMANTO.domain.ReviewImg;
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
}
