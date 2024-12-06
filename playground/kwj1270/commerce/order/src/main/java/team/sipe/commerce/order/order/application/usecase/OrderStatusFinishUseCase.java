package team.sipe.commerce.order.order.application.usecase;

public interface OrderStatusFinishUseCase {
    Long changeFinish(Long userId, Long orderId);
}
