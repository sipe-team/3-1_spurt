package team.sipe.commerce.refund;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RefundMethodName {

    NAVER_PAY,
    KAKAO_PAY,
    TOSS_PAY
}
