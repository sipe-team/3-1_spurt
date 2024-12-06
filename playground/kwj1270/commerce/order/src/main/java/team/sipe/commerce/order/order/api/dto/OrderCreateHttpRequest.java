package team.sipe.commerce.order.order.api.dto;

import team.sipe.commerce.order.order.application.command.OrderCreateCommand;

public record OrderCreateHttpRequest(
        long productId,
        long shopId,
        long userId,
        int quantity
) {
    public OrderCreateCommand toCommand() {
        return new OrderCreateCommand(
                productId,
                shopId,
                userId,
                quantity
        );
    }
}
