package team.sipe.commerce.shop.product.domain;

@FunctionalInterface
public interface ProductRepository {
    Product register(Product product);
}
