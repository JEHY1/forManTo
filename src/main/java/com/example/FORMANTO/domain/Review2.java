package com.example.FORMANTO.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "review_tb")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Review2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", updatable = false)
    private Long reviewId;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "product_group_id", nullable = false)
    private Long productGroupId;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder
    public Review2(Long customerId, Long productGroupId, LocalDateTime date, String content) {
        this.customerId = customerId;
        this.productGroupId = productGroupId;
        this.date = date;
        this.content = content;
    }
}
