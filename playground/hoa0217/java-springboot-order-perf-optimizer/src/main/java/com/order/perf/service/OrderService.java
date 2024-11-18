package com.order.perf.service;

import com.order.perf.domain.Delivery;
import com.order.perf.domain.Order;
import com.order.perf.domain.OrderProduct;
import com.order.perf.domain.Payment;
import com.order.perf.domain.Product;
import com.order.perf.domain.Refund;
import com.order.perf.dto.response.OrderDetailsResponse;
import com.order.perf.repository.DeliveryRepository;
import com.order.perf.repository.OrderProductRepository;
import com.order.perf.repository.OrderRepository;
import com.order.perf.repository.PaymentRepository;
import com.order.perf.repository.ProductRepository;
import com.order.perf.repository.RefundRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

  private final OrderRepository orderRepository;
  private final OrderProductRepository orderProductRepository;
  private final ProductRepository productRepository;
  private final DeliveryRepository deliveryRepository;
  private final PaymentRepository paymentRepository;
  private final RefundRepository refundRepository;

  public OrderDetailsResponse findOrder(Long orderId) {
    Order order = orderRepository.findById(orderId)
        .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));

    OrderProduct orderProduct = orderProductRepository.findByOrderId(orderId)
        .orElseThrow(() -> new RuntimeException("주문 상품을 찾을 수 없습니다."));

    Product product = productRepository.findById(orderProduct.getProductId())
        .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));

    Delivery delivery = deliveryRepository.findByOrderId(orderId)
        .orElseThrow(() -> new RuntimeException("배송 정보를 찾을 수 없습니다."));

    Payment payment = paymentRepository.findByOrderId(orderId)
        .orElseThrow(() -> new RuntimeException("결제 정보를 찾을 수 없습니다."));

    Refund refund = refundRepository.findByOrderId(orderId)
        .orElseThrow(() -> new RuntimeException("환불 정보을 찾을 수 없습니다."));

    return null;
  }
}
