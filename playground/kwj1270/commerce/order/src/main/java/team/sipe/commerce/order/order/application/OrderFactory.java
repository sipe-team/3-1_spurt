package team.sipe.commerce.order.order.application;

import org.springframework.stereotype.Service;
import team.sipe.commerce.order.order.application.command.OrderCreateCommand;
import team.sipe.commerce.order.order.domain.*;
import team.sipe.commerce.order.order.domain.product.OrderProduct;
import team.sipe.commerce.order.order.domain.product.OrderProductOption;
import team.sipe.commerce.order.order.domain.product.OrderProductOptionGroup;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class OrderFactory {

    public Order init(final OrderCreateCommand command, final OrderProduct orderProduct) {
        final long orderProductId = orderProduct.getOrderProductId();
        final long productId = command.productId();
        if(orderProductId != productId) {
            throw new IllegalArgumentException("Product not found");
        }
        return new Order(
                null,
                command.userId(),
                command.shopId(),
                orderLineItem(command, orderProduct),
                OrderStatus.ORDERED,
                OrderPaymentMethod.KAKAO_PAY,
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
        );
    }

    private OrderLineItem orderLineItem(final OrderCreateCommand command, final OrderProduct orderProduct) {
        return new OrderLineItem(
                null,
                orderProduct.getOrderProductId(),
                orderProduct.getOrderProductName(),
                command.quantity(),
                orderProduct.getProductOptionGroups().stream()
                        .map(this::orderOptionGroup)
                        .toList(),
                orderProduct.getCreatedAt(),
                orderProduct.getUpdatedAt()
        );
    }

    private OrderOptionGroup orderOptionGroup(final OrderProductOptionGroup orderProductOptionGroup) {
        return new OrderOptionGroup(
                null,
                orderProductOptionGroup.getOrderProductOptionGroupName(),
                orderProductOptionGroup.getOrderProductOptions().stream()
                        .map(this::orderOption)
                        .collect(Collectors.toList()),
                orderProductOptionGroup.getCreatedAt(),
                orderProductOptionGroup.getUpdatedAt()
        );
    }

    private OrderOption orderOption(final OrderProductOption orderProductOption) {
        return new OrderOption(
                null,
                orderProductOption.getOrderProductOptionId(),
                orderProductOption.getOrderProductOptionName(),
                new Price(orderProductOption.getProductPrice()),
                orderProductOption.getCreatedAt(),
                orderProductOption.getUpdatedAt()
        );
    }
}
