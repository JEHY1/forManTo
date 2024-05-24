package com.example.FORMANTO.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Entity
@Getter
@Table(name = "img_tb")

public class Img {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "img_id", updatable = false)
    private Long img_id;

    @Column (name = "product_group_id", nullable = false)
    private Long product_group_id;

    @Column (name = "src", nullable = false)
    private String src;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "")

    @Builder
    public Img(Long product_group_id, String src){

        this.product_group_id = product_group_id;
        this.src = src;

    }
}