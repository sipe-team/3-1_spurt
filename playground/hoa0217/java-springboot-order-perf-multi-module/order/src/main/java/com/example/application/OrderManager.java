package com.example.application;

import com.example.application.dto.OrderSheet;
import com.example.domain.Order;
import com.example.domain.OrderLine;
import com.example.domain.OrderLineMapper;
import com.example.domain.OrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderManager {

    private OrderRepository orderRepository;
    private OrderLineMapper orderLineMapper;

    public void order(OrderSheet orderSheet){
        final List<OrderLine> lines = orderLineMapper.map(orderSheet.items());
        final Order order = new Order(orderSheet.userId(), lines);
        orderRepository.save(order);
    }
}
