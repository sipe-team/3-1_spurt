package com.example.infra;

import com.example.application.dto.OrderSheetItem;
import com.example.domain.OrderLine;
import com.example.domain.OrderLineMapper;
import com.example.domain.OrderProduct;
import com.example.infra.catalog.Product;
import com.example.infra.catalog.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCatalogHandler implements OrderLineMapper {

    private final ProductRepository productRepository;

    @Override
    public List<OrderLine> map(final List<OrderSheetItem> orderSheetItems) {
        return orderSheetItems.stream().map(orderSheetItem -> {
            Product product = getProductFor(orderSheetItem);
            final OrderProduct orderProduct = new OrderProduct(product.id(), product.name(),
                    product.price());
            return new OrderLine(orderProduct, orderSheetItem.quantity());
        }).collect(Collectors.toList());
    }

    private Product getProductFor(OrderSheetItem item) {
        return productRepository.findProduct(item.productId());
    }
}
