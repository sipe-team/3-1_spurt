package team.sipe.commerce.order.order.application;

import team.sipe.commerce.order.order.application.command.OrderCreateCommand;

public interface OrderCreateUseCase {
    Long create(OrderCreateCommand command);
}
