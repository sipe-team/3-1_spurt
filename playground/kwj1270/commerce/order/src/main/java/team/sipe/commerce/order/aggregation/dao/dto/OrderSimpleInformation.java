package team.sipe.commerce.order.aggregation.dao.dto;

public record OrderSimpleInformation(
        Long orderId,
        String orderStatus
) {
}
