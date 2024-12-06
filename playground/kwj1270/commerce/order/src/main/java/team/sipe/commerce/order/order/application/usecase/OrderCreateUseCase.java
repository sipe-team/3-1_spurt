package team.sipe.commerce.order.order.application.usecase;

import team.sipe.commerce.order.order.application.command.OrderCreateCommand;

public interface OrderCreateUseCase {
    Long create(OrderCreateCommand command);
}
