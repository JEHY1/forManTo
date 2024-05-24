package com.example.FORMANTO.service;

import com.example.FORMANTO.domain.Payment;
import com.example.FORMANTO.domain.PaymentDetail;
import com.example.FORMANTO.domain.SaleProduct;
import com.example.FORMANTO.dto.order.PaymentRequest;
import com.example.FORMANTO.repository.CartRepository;
import com.example.FORMANTO.repository.PaymentDetailRepository;
import com.example.FORMANTO.repository.PaymentRepository;
import com.example.FORMANTO.repository.SaleProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final SaleProductRepository saleProductRepository;
    private final PaymentDetailRepository paymentDetailRepository;
    private final ProductService productService;
    private final CustomerService customerService;
    private final CartRepository cartRepository;

    public Payment payment (PaymentRequest request, Principal principal){
        System.err.println(request.toString());
        if(principal == null){ //비회원 결제시
            System.err.println("payment notmember");
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
                        .reviewDeadline(LocalDateTime.now().plusDays(30))
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
        else{
            System.err.println("payment member");
            Long paymentId = paymentRepository.save(Payment.builder() //결제 테이블 추가후 id값 가져오기
                    .customerId(customerService.findByUsername(principal.getName()).getCustomerId())
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
                            .reviewDeadline(LocalDateTime.now().plusDays(30))
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

            request.getCartIds().forEach(cartId -> cartRepository.deleteById(cartId));

            return paymentRepository.findById(paymentId).orElseThrow(() -> new IllegalArgumentException("not created"));
        }

    }
}
