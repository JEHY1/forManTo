package com.example.FORMANTO.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sale_product_tb")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaleProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_product_id", updatable = false)
    private Long saleProductId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "payment_id", nullable = false)
    private Long paymentId;

    @Column(name = "total_quantity", nullable = false)
    private int totalQuantity;

    @Builder
    public SaleProduct(Long productId, Long paymentId, int totalQuantity) {
        this.productId = productId;
        this.paymentId = paymentId;
        this.totalQuantity = totalQuantity;
    }
}
