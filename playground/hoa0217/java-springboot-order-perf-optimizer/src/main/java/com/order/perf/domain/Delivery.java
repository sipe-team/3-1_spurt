package com.order.perf.domain;

import com.order.perf.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "DELIVERYS")
@Entity
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String recipientName; // 받으실 분
    private String mobile; // 연락처
    private String address; // 배송 주소
    private String zipCode; // 우편 번호
    private String storePassword; // 가게 비밀 번호
    private String deliveryMomo; // 배송 메모
    private Long orderId;

    protected Delivery() {
    }

    public Delivery(final String recipientName, final String mobile,
                    final String address, final String zipCode,
                    final String storePassword,
                    final String deliveryMomo) {
        this.recipientName = recipientName;
        this.mobile = mobile;
        this.address = address;
        this.zipCode = zipCode;
        this.storePassword = storePassword;
        this.deliveryMomo = deliveryMomo;
    }

    public Long getId() {
        return id;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getStorePassword() {
        return storePassword;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getDeliveryMomo() {
        return deliveryMomo;
    }
}
