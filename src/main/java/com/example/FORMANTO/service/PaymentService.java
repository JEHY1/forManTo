package com.example.FORMANTO.service;

import com.example.FORMANTO.domain.Payment;
import com.example.FORMANTO.domain.PaymentDetail;
import com.example.FORMANTO.domain.SaleProduct;
import com.example.FORMANTO.dto.PaymentRequest;
import com.example.FORMANTO.repository.PaymentDetailRepository;
import com.example.FORMANTO.repository.PaymentRepository;
import com.example.FORMANTO.repository.SaleProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final SaleProductRepository saleProductRepository;
    private final PaymentDetailRepository paymentDetailRepository;
    private final ProductService productService;

    public Payment payment (PaymentRequest request, Principal principal){

        if(principal == null){ //비회원 결제시
            Long paymentId = paymentRepository.save(Payment.builder() //결제 테이블 추가후 id값 가져오기
                .paymentPrice(request.getPaymentPrice())
                .paymentType(request.getPaymentType())
                .address(request.getAddress())
                .status("배송준비")
                .deliveryFee(request.getDeliveryFee())
                .receiver(request.getReceiver())
                .receiverPhone(request.getReceiverPhone())
                .build()).getPaymentId();

            if(!request.getProductIds().isEmpty()){
                int index = 0;
                List<Integer> productCounts = request.getProductCounts();

                for(Long productId : request.getProductIds()){
                    SaleProduct saleProduct = saleProductRepository.save(SaleProduct.builder()
                        .paymentId(paymentId)
                        .productId(productId)
                        .totalQuantity(productCounts.get(index++))
                        .build());

                    for(int i = 0; i < saleProduct.getTotalQuantity(); i++){
                        paymentDetailRepository.save(PaymentDetail.builder()
                            .saleProductId(saleProduct.getSaleProductId())
                            .status("배송준비")
                            .productPrice(productService.findProductByProductId(productId).getPrice())
                            .build());
                    }
                }
            }
            return paymentRepository.findById(paymentId).orElseThrow(() -> new IllegalArgumentException("not created"));
        }
        return null;
    }
}
