package team.sipe.commerce.domain;

import team.sipe.commerce.common.BaseEntity;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Table(name = "PAYMENTS")
@Entity
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String paymentMethod; // 결제 수단 ex) BILLING_NAVER_PAY
    private String paymentMethodName; // 결제 수단 이름 ex) 네이버
    private int paymentAmount; // 총 결제 금액

    protected Payment() {
    }

    public Payment(final String paymentMethod, final String paymentMethodName, final int paymentAmount) {
        this.paymentMethod = paymentMethod;
        this.paymentMethodName = paymentMethodName;
        this.paymentAmount = paymentAmount;
    }
}
