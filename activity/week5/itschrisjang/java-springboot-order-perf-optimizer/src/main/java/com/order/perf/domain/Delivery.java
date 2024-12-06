package com.order.perf.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@Getter
@Table("deliveries")
public class Delivery {

    @Id
    @Column("delivery_id")
    private Long id;

    @Column("recipient_name")
    private String recipientName; // 받으실 분

    @Column("mobile")
    private String mobile; // 연락처

    @Column("address")
    private String address; // 배송 주소

    @Column("zip_code")
    private String zipCode; // 우편 번호

    @Column("store_password")
    private String storePassword; // 가게 비밀 번호

    @Column("delivery_memo")
    private String deliveryMemo; // 배송 메모

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    protected Delivery() {
    }

    public Delivery(final String recipientName, final String mobile,
                    final String address, final String zipCode,
                    final String storePassword,
                    final String deliveryMemo) {
        this.recipientName = recipientName;
        this.mobile = mobile;
        this.address = address;
        this.zipCode = zipCode;
        this.storePassword = storePassword;
        this.deliveryMemo = deliveryMemo;
    }
}
