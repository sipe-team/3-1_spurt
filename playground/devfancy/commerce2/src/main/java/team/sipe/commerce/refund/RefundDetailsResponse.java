package team.sipe.commerce.refund;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

// 환불 정보 DTO
@Getter
public class RefundDetailsResponse {

    private final RefundMethodName refundMethodName;
    private final int refundAmount;
    private final RefundStatus refundStatus;

    @JsonCreator
    @Builder
    public RefundDetailsResponse(
            @JsonProperty("refundMethodName") final RefundMethodName refundMethodName,
            @JsonProperty("refundAmount") final int refundAmount,
            @JsonProperty("refundStatus") final RefundStatus refundStatus) {
        this.refundMethodName = refundMethodName;
        this.refundAmount = refundAmount;
        this.refundStatus = refundStatus;
    }

    public static RefundDetailsResponse from(final Refund refund) {
        return RefundDetailsResponse.builder()
                .refundMethodName(refund.getRefundMethodName())
                .refundAmount(refund.getRefundAmount())
                .refundStatus(RefundStatus.PENDING)
                .build();
    }
}
