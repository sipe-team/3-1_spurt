package com.order.perf.domain;

import com.order.perf.common.BaseEntity;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Table(name = "DELIVERYS")
@Entity
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id", nullable = false)
    private Long id;

    @Column(name = "recipient_name", nullable = false)
    private String recipientName; // 받으실 분

    @Column(name = "mobile", nullable = false)
    private String mobile; // 연락처

    @Column(name = "address", nullable = false)
    private String address; // 배송 주소

    @Column(name = "zip_code", nullable = false)
    private String zipCode; // 우편 번호

    @Column(name = "store_password", nullable = false)
    private String storePassword; // 가게 비밀 번호

    @Column(name = "delivery_memo", nullable = false)
    private String deliveryMemo; // 배송 메모

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
