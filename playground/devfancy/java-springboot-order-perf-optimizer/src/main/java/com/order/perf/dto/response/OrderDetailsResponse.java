package com.order.perf.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

// 특정 주문의 상세 정보를 조회하는 API에 사용하는 DTO


@Getter
public class OrderDetailsResponse {

    private final List<OrderDetailItemDto> orderItems;

    public OrderDetailsResponse(final List<OrderDetailItemDto> orderItems) {
        this.orderItems = orderItems;
    }

}