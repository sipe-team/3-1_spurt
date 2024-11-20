package com.order.perf.service;

import com.order.perf.domain.Order;
import com.order.perf.domain.OrderProduct;
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
import java.util.concurrent.CompletableFuture;
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
    OrderResponse order = findOrder(orderId);
    List<ProductResponse> products = findProducts(orderId);
    DeliveryResponse delivery = findDelivery(orderId);
    RefundResponse refund = findRefund(orderId);

    return new OrderDetailResponse(
        order,
        products,
        delivery,
        refund
    );
  }

  public OrderDetailResponse findOrderDetailAsync(Long orderId) {
    CompletableFuture<OrderResponse> orderFuture = CompletableFuture.supplyAsync(() -> findOrder(orderId));
    CompletableFuture<List<ProductResponse>> productsFuture = CompletableFuture.supplyAsync(() -> findProducts(orderId));
    CompletableFuture<DeliveryResponse> deliveryFuture = CompletableFuture.supplyAsync(() -> findDelivery(orderId));
    CompletableFuture<RefundResponse> refundFuture = CompletableFuture.supplyAsync(() -> findRefund(orderId));

    CompletableFuture.allOf(orderFuture, productsFuture, deliveryFuture, refundFuture).join();

    return new OrderDetailResponse(
        orderFuture.join(),
        productsFuture.join(),
        deliveryFuture.join(),
        refundFuture.join()
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
    return deliveryRepository.findByOrderId(orderId)
        .map(delivery -> new DeliveryResponse(
            delivery.getRecipientName(),
            delivery.getMobile(),
            delivery.getAddress(),
            delivery.getZipCode(),
            delivery.getStorePassword(),
            delivery.getDeliveryMomo()
        ))
        .orElse(null);
  }

  private RefundResponse findRefund(Long orderId) {
    return refundRepository.findByOrderId(orderId)
        .map(refund -> new RefundResponse(
            refund.getRefundMethodName(),
            refund.getRefundAmount(),
            refund.getRefundStatus()
        ))
        .orElse(null);
  }
}
