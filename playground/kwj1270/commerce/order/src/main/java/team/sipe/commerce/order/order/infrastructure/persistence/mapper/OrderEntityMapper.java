package team.sipe.commerce.order.order.infrastructure.persistence.mapper;

import team.sipe.commerce.order.order.domain.*;
import team.sipe.commerce.order.order.infrastructure.persistence.OrderEntity;
import team.sipe.commerce.order.order.infrastructure.persistence.OrderLineItemEntity;
import team.sipe.commerce.order.order.infrastructure.persistence.OrderOptionEntity;
import team.sipe.commerce.order.order.infrastructure.persistence.OrderOptionGroupEntity;

public class OrderEntityMapper {
    private OrderEntityMapper() {
    }

    public static OrderEntity toEntity(final Order order) {
        return new OrderEntity(
                order.getId(),
                order.getUserId(),
                order.getShopId(),
                toEntity(order.getOrderLineItem()),
                order.getOrderPaymentMethod().name(),
                order.getOrderStatus().name(),
                order.getCreatedAt(),
                order.getUpdatedAt(),
                order.getOrderedAt()
        );
    }

    private static OrderLineItemEntity toEntity(final OrderLineItem orderLineItem) {
        return new OrderLineItemEntity(
                orderLineItem.getOrderLineItemId(),
                orderLineItem.getOrderOptionGroups().stream()
                        .map(OrderEntityMapper::toEntity)
                        .toList(),
                orderLineItem.getProductId(),
                orderLineItem.getOrderLineName(),
                orderLineItem.getOrderLineQuantity(),
                orderLineItem.getCreatedAt(),
                orderLineItem.getUpdatedAt()
        );
    }

    private static OrderOptionGroupEntity toEntity(final OrderOptionGroup orderOptionGroup) {
        return new OrderOptionGroupEntity(
                orderOptionGroup.getOrderOptionGroupId(),
                orderOptionGroup.getOrderOptionGroupName(),
                orderOptionGroup.getOrderOptions().stream()
                        .map(OrderEntityMapper::toEntity)
                        .toList(),
                orderOptionGroup.getCreatedAt(),
                orderOptionGroup.getUpdatedAt()
        );
    }

    private static OrderOptionEntity toEntity(final OrderOption orderOption) {
        return new OrderOptionEntity(
                orderOption.getOrderOptionId(),
                orderOption.getProductOptionId(),
                orderOption.getOrderOptionName(),
                orderOption.getOrderPrice().getPrice(),
                orderOption.getCreatedAt(),
                orderOption.getUpdatedAt()
        );
    }

    public static Order toDomain(OrderEntity orderEntity) {
        return new Order(
                orderEntity.getId(),
                orderEntity.getUserId(),
                orderEntity.getShopId(),
                toDomain(orderEntity.getOrderLineItem()),
                OrderStatus.valueOf(orderEntity.getOrderStatus()),
                OrderPaymentMethod.valueOf(orderEntity.getOrderPaymentMethod()),
                orderEntity.getCreatedAt(),
                orderEntity.getUpdatedAt(),
                orderEntity.getOrderedAt()
        );
    }

    private static OrderLineItem toDomain(final OrderLineItemEntity orderLineItemEntity) {
        return new OrderLineItem(
                orderLineItemEntity.getOrderLineItemId(),
                orderLineItemEntity.getProductId(),
                orderLineItemEntity.getOrderLineName(),
                orderLineItemEntity.getOrderLineQuantity(),
                orderLineItemEntity.getOrderOptionGroups().stream()
                        .map(OrderEntityMapper::toDomain)
                        .toList(),
                orderLineItemEntity.getCreatedAt(),
                orderLineItemEntity.getUpdatedAt()
        );
    }

    private static OrderOptionGroup toDomain(OrderOptionGroupEntity orderOptionGroupEntity) {
        return new OrderOptionGroup(
                orderOptionGroupEntity.getOrderOptionGroupId(),
                orderOptionGroupEntity.getOrderOptionGroupName(),
                orderOptionGroupEntity.getOrderOptions().stream()
                        .map(OrderEntityMapper::toDomain)
                        .toList(),
                orderOptionGroupEntity.getCreatedAt(),
                orderOptionGroupEntity.getUpdatedAt()
        );
    }

    private static OrderOption toDomain(OrderOptionEntity orderOptionEntity) {
        return new OrderOption(
                orderOptionEntity.getOrderOptionId(),
                orderOptionEntity.getProductOptionId(),
                orderOptionEntity.getOrderOptionName(),
                new Price(orderOptionEntity.getOrderOptionPrice()),
                orderOptionEntity.getCreatedAt(),
                orderOptionEntity.getUpdatedAt()
        );
    }
}
