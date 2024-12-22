package com.example.application;

import com.example.application.dto.ProductResponse;
import com.example.application.dto.ProductsResponse;
import com.example.config.CustomCacheManager;
import com.example.config.LocalCacheManager;
import com.example.config.RemoteCacheManager;
import com.example.domain.Brand;
import com.example.domain.BrandRepository;
import com.example.domain.Category;
import com.example.domain.CategoryRepository;
import com.example.domain.Product;
import com.example.domain.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DisplayManager {

    private static final String CACHE_KEY = "products";
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final LocalCacheManager localCacheManager;
    private final RemoteCacheManager remoteCacheManager;

    public ProductsResponse findAllProduct() {
        return getProductsResponse();
    }

    public ProductsResponse findAllProductLocalCache() {
        return findAllProductFromCache(localCacheManager, "local");
    }

    public ProductsResponse findAllProductRemoteCache() {
        return findAllProductFromCache(remoteCacheManager, "remote");
    }

    public ProductsResponse findAllProductHybridCache() {
        final ProductsResponse localProduct = (ProductsResponse) localCacheManager.get(CACHE_KEY);
        if (localProduct != null) {
            logCacheHit("local", localProduct);
            return localProduct;
        }

        // 로컬캐시에 없고 remote 캐시에 있다면 로컬캐시 저장 후 반환
        final ProductsResponse remoteProducts = (ProductsResponse) remoteCacheManager.get(
                CACHE_KEY);
        if (remoteProducts != null) {
            logCacheHit("remote", remoteProducts);
            localCacheManager.set(CACHE_KEY, remoteProducts);
            return remoteProducts;
        }

        // 로컬캐시, 리모트캐시 둘다 없다면 새롭게 조회하여 둘다 저장 후 반환
        final ProductsResponse product = getProductsResponse();
        localCacheManager.set(CACHE_KEY, product);
        remoteCacheManager.set(CACHE_KEY, product);
        return product;
    }

    private ProductsResponse findAllProductFromCache(CustomCacheManager cacheManager,
            String cacheName) {
        final ProductsResponse cacheProduct = (ProductsResponse) cacheManager.get(CACHE_KEY);
        if (cacheProduct != null) {
            logCacheHit(cacheName, cacheProduct);
            return cacheProduct;
        }
        final ProductsResponse product = getProductsResponse();
        cacheManager.set(CACHE_KEY, product);
        return product;
    }

    private void logCacheHit(String cacheName, ProductsResponse products) {
        log.info("find success at " + cacheName + " cache: " + products.products().size());
    }

    private ProductsResponse getProductsResponse() {
        final List<Product> products = productRepository.findAll();
        log.info("find success at db: " + products.size());
        final List<ProductResponse> responses = products.stream()
                .map(product -> {
                    final Brand brand = brandRepository.findById(product.brandId());
                    final Category category = categoryRepository.findById(product.categoryId());
                    return new ProductResponse(
                            product.id(),
                            brand.id(),
                            brand.name(),
                            category.id(),
                            category.name(),
                            product.name(),
                            product.price(),
                            product.description()
                    );
                }).toList();
        return new ProductsResponse(responses);
    }
}
