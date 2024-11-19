package com.order.perf.service;

import com.order.perf.domain.Delivery;
import com.order.perf.domain.Order;
import com.order.perf.domain.OrderProduct;
import com.order.perf.domain.Refund;
import com.order.perf.dto.DeliveryResponse;
import com.order.perf.dto.OrderDetailResponse;
import com.order.perf.dto.OrderResponse;
import com.order.perf.dto.ProductResponse;
import com.order.perf.dto.RefundResponse;
import com.order.perf.repository.DeliveryRepository;
import com.order.perf.repository.OrderProductRepository;
import com.order.perf.repository.OrderRepository;
import com.order.perf.repository.ProductRepository;
import com.order.perf.repository.RefundRepository;
import java.util.List;
import java.util.Optional;
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
  private final RefundRepository refundRepository;

  public OrderDetailResponse findOrderDetail(Long orderId) {

    return new OrderDetailResponse(
        findOrder(orderId),
        findProducts(orderId),
        findDelivery(orderId),
        findRefund(orderId)
    );
  }

  private OrderResponse findOrder(Long orderId) {
    Order order = orderRepository.findById(orderId)
        .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));

    return new OrderResponse(
        order.getId(),
        order.getOrderNumber(),
        order.getOrderStatus(),
        order.getCreatedAt(),
        order.getUpdatedAt()
    );
  }

  private List<ProductResponse> findProducts(Long orderId) {
    List<OrderProduct> orderProducts = orderProductRepository.findByOrderId(orderId);

    return orderProducts.stream()
        .map(orderProduct -> productRepository.findById(orderProduct.getProductId())
            .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다.")))
        .map(product -> new ProductResponse(
            product.getId(),
            product.getProductName(),
            product.getDescription(),
            product.getPrice(),
            product.getBundleName(),
            product.getBundleQuantity()
        ))
        .toList();
  }

  private DeliveryResponse findDelivery(Long orderId) {
    Optional<Delivery> optionalDelivery = deliveryRepository.findByOrderId(orderId);
    if (optionalDelivery.isEmpty()) {
      return null;
    }

    Delivery delivery = optionalDelivery.get();
    return new DeliveryResponse(
        delivery.getRecipientName(),
        delivery.getMobile(),
        delivery.getAddress(),
        delivery.getZipCode(),
        delivery.getStorePassword(),
        delivery.getDeliveryMomo()
    );
  }

  private RefundResponse findRefund(Long orderId) {
    Optional<Refund> optionalRefund = refundRepository.findByOrderId(orderId);
    if (optionalRefund.isEmpty()) {
      return null;
    }

    Refund refund = optionalRefund.get();
    return new RefundResponse(
        refund.getRefundMethodName(),
        refund.getRefundAmount(),
        refund.getRefundStatus()
    );
  }
}
