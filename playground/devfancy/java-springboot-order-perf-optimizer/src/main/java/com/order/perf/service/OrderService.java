package com.order.perf.service;

import com.order.perf.dto.OrderItemDto;
import com.order.perf.dto.response.OrderDetailItemDto;
import com.order.perf.dto.response.OrderDetailsResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class OrderService {


    public List<OrderDetailItemDto> getOrderDetailsResponse(Integer orderId) {
        return null;
    }
}
