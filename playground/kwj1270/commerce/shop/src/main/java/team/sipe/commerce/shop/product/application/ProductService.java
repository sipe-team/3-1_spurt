package team.sipe.commerce.shop.product.application;

import org.springframework.stereotype.Service;
import team.sipe.commerce.shop.product.application.mapper.ProductCommandMapper;
import team.sipe.commerce.shop.product.application.command.ProductRegisterCommand;
import team.sipe.commerce.shop.product.domain.Product;
import team.sipe.commerce.shop.product.domain.ProductRepository;

@Service
public class ProductService implements ProductRegisterUseCase {

    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Long register(final ProductRegisterCommand command) {
        final Product product = productRepository.register(ProductCommandMapper.init(command));
        return product.getId();
    }
}
