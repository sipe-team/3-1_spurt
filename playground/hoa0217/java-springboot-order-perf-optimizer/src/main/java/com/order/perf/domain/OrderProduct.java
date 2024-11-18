package com.order.perf.domain;

import com.order.perf.common.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Table(name = "ORDER_PRODUCTS")
@Entity
public class OrderProduct extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long orderId;
  @Getter
  private Long productId;

  protected OrderProduct() {
  }

  public OrderProduct(Long id, Long orderId, Long productId, int quantity) {
    this.id = id;
    this.orderId = orderId;
    this.productId = productId;
  }
}
