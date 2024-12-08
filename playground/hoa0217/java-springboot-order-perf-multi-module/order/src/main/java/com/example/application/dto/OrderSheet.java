package com.example.application.dto;

import java.util.List;

public record OrderSheet(
        Long userId,
        List<OrderSheetItem> items,
        String recipientName,
        String mobile,
        String address,
        String zipCode,
        String deliveryMemo
) {

}
