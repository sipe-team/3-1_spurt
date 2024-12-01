package com.order.perf.dto.response;

import com.order.perf.dto.DeliveryDetailsDto;
import com.order.perf.dto.ProductBundleDetailsDto;
import com.order.perf.dto.ProductDetailsDto;
import com.order.perf.dto.RefundDetailsDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderDetailItemDto {
    private ProductBundleDetailsDto productBundleDetailDto;
    private ProductDetailsDto productDetailsDto;
    private DeliveryDetailsDto deliveryDetailsDto;
    private RefundDetailsDto refundDetailsDto;
}