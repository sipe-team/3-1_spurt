package com.order.perf.dto.response;

import lombok.Getter;

import java.util.List;

// 특정 주문의 상세 정보를 조회하는 API에 사용하는 DTO
@Getter
public class OrderDetailsResponse {

    private final List<OrderItemDto> orderItems;

    public OrderDetailsResponse(final List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }

    class OrderItemDto {
        private
        class ProductBundleDetailDto {
            // 상품 정보 1: 상품의 묶음 관련 상세 정보(상품 묶음 이름, 해당 묶음에 포함된 상품의 수량)
        }
        class ProductDetailsDto {
            // 상품 정보 2: 실제 상품의 상세 정보 (상품 이름, 상품 설명, 상품 가격 등)
        }
        class DeliveryDetailsDto {
            // 배송 정보: 배송과 관련된 상세 정보(받으실 분, 연락처, 배송 주소, 우편 번호, 가게 비밀 번호, 배송 메모 등)
        }
        class RefundDetailsDto {
            // 환불 정보: 환불 관련 상세 정보 (환불 수단, 환불 총 금액, 환불 처리 상태 등)
        }
    }
}
