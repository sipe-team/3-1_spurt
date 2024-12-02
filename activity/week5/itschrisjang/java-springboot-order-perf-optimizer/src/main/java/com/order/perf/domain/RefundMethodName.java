package com.order.perf.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RefundMethodName {

    NAVER_PAY,
    KAKAO_PAY,
    TOSS_PAY
    ;

    public static RefundMethodName fromString(String value) {
        for (RefundMethodName methodName : RefundMethodName.values()) {
            if (methodName.name().equalsIgnoreCase(value)) {
                return methodName;
            }
        }
        throw new IllegalArgumentException("Unknown refund method: " + value);
    }
}
