package team.sipe.commerce.delivery.delivery.infrastructure.persistence;

import jakarta.persistence.*;

import java.util.Objects;

@Table(name = "delivery")
@Entity
public class DeliveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "order_id", nullable = false)
    private String orderId;

    @Column(name = "recipient_name", nullable = false)
    private String recipientName;

    @Column(name = "mobile", nullable = false)
    private String mobile;

    @Column(name = "delivery_memo", nullable = false)
    private String deliveryMemo;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Column(name = "delivery_status", nullable = false)
    private String deliveryStatus;

    public DeliveryEntity() {
    }

    public DeliveryEntity(final String id, final String orderId, final String recipientName,
                          final String mobile, final String deliveryMemo, final String address,
                          final String zipCode, final String deliveryStatus) {
        this.id = id;
        this.orderId = orderId;
        this.recipientName = recipientName;
        this.mobile = mobile;
        this.deliveryMemo = deliveryMemo;
        this.address = address;
        this.zipCode = zipCode;
        this.deliveryStatus = deliveryStatus;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final DeliveryEntity that = (DeliveryEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
