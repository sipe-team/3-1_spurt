package team.sipe.commerce.shop.product.application;

import org.springframework.stereotype.Service;
import team.sipe.commerce.shop.product.application.command.ProductRegisterCommand;
import team.sipe.commerce.shop.product.application.mapper.ProductFactory;
import team.sipe.commerce.shop.product.domain.Product;
import team.sipe.commerce.shop.product.domain.ProductRepository;

@Service
public class ProductService implements ProductRegisterUseCase {

    private final ProductRepository productRepository;
    private final ProductFactory productFactory;

    public ProductService(final ProductRepository productRepository,
                          final ProductFactory productFactory) {
        this.productRepository = productRepository;
        this.productFactory = productFactory;
    }

    @Override
    public Long register(final ProductRegisterCommand command) {
        final Product product = productRepository.register(productFactory.init(command));
        return product.getId();
    }
}
