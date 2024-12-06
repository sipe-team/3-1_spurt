package com.order.perf.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@Getter
@Table("payments")
public class Payment {

    @Id
    @Column("payment_id")
    private String id;

    @Column("payment_method")
    private String paymentMethod; // 결제 수단

    @Column("payment_method_name")
    private String paymentMethodName; // 결제 수단 이름

    @Column("payment_amount")
    private int paymentAmount; // 총 결제 금액

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public Payment() {}

    public Payment(final String paymentMethod, final String paymentMethodName, final int paymentAmount) {
        this.paymentMethod = paymentMethod;
        this.paymentMethodName = paymentMethodName;
        this.paymentAmount = paymentAmount;
    }

    public void declareEncryptedShardRoutingId(String id) {
        this.id = id;
    }
}
