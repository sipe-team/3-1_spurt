package team.sipe.commerce.dto;


import team.sipe.commerce.domain.Refund;
import team.sipe.commerce.domain.RefundMethodName;
import team.sipe.commerce.domain.RefundStatus;
import lombok.Builder;
import lombok.Getter;

// 환불 정보 DTO
@Getter
public class RefundDetailsDto {

    private final RefundMethodName refundMethodName;
    private final int refundAmount;
    private final RefundStatus refundStatus;

    @Builder
    public RefundDetailsDto(final RefundMethodName refundMethodName, final int refundAmount, final RefundStatus refundStatus) {
        this.refundMethodName = refundMethodName;
        this.refundAmount = refundAmount;
        this.refundStatus = refundStatus;
    }

    public static RefundDetailsDto from(final Refund refund) {
        return RefundDetailsDto.builder()
                .refundMethodName(refund.getRefundMethodName())
                .refundAmount(refund.getRefundAmount())
                .refundStatus(RefundStatus.PENDING)
                .build();
    }
}
