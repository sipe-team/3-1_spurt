package team.sipe.commerce.order.order.application.command;

import team.sipe.common.CommandValidating;

public record OrderCreateCommand(
        long productId,
        long shopId,
        long userId,
        int quantity
) implements CommandValidating<OrderCreateCommand> {
}
