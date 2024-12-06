package team.sipe.commerce.shop.aggregation.api;

import org.jooq.generated.tables.pojos.Shops;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.sipe.commerce.shop.aggregation.api.dto.ProductInfoHttpResponse;
import team.sipe.commerce.shop.aggregation.api.dto.ShopInfoHttpResponse;
import team.sipe.commerce.shop.aggregation.api.dto.ShopInformationResponse;
import team.sipe.commerce.shop.aggregation.api.dto.ShopProductInformationHttpResponse;
import team.sipe.commerce.shop.aggregation.dao.ShopProductQueryDao;
import team.sipe.commerce.shop.aggregation.dao.dto.ProductInformation;
import team.sipe.commerce.shop.aggregation.dao.dto.ShopProductInformation;
import team.sipe.common.dto.ProductHttpResponse;
import team.sipe.common.dto.ProductOptionGroupHttpResponse;
import team.sipe.common.dto.ProductOptionHttpResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/query")
@RestController
public class AggregationApi {

    private final ShopProductQueryDao shopProductQueryDao;

    public AggregationApi(final ShopProductQueryDao shopProductQueryDao) {
        this.shopProductQueryDao = shopProductQueryDao;
    }

    @GetMapping("/shops/{shopId}")
    public ResponseEntity<ShopInformationResponse> findShop(@PathVariable("shopId") final Long shopId) {
        final Shops shops = shopProductQueryDao.findShopById(shopId);
        final ShopInformationResponse shopInformationResponse = new ShopInformationResponse(shops.getId(), shops.getSellerId(), shops.getName(), shops.getDescription(), shops.getAddress(), shops.getCreatedAt(), shops.getUpdatedAt());
        return ResponseEntity.ok().body(shopInformationResponse);
    }

    @GetMapping("/shops/{shopId}/products")
    public ResponseEntity<ShopProductInformationHttpResponse> findAllProductsByShopId(
            @PathVariable("shopId") final Long shopId,
            @RequestParam(value = "offset", defaultValue = "0") final Long offset,
            @RequestParam(value = "limit", defaultValue = "10") final Long limit
    ) {
        final List<ShopProductInformation> test = shopProductQueryDao.findAllProductsByShopId(shopId, offset, limit);
        final Map<Long, List<ShopProductInformation>> shopProductListMap = test
                .stream()
                .collect(Collectors.groupingBy(ShopProductInformation::shopId));
        if(shopProductListMap.containsKey(shopId)){
            final List<ShopProductInformation> shopProductInformationList = shopProductListMap.get(shopId);
            return ResponseEntity.ok().body(new ShopProductInformationHttpResponse(
                    getShopInfo(shopProductInformationList),
                    getProductInfo(shopProductInformationList)
            ));
        }
        return ResponseEntity.ok().body(new ShopProductInformationHttpResponse(null, null));
    }

    @GetMapping("/shops/{shopId}/products/{productId}")
    public ResponseEntity<ProductHttpResponse> product(@PathVariable("shopId") final Long shopId,
                                                       @PathVariable("productId") final Long productId
    ) {
        final ProductInformation productDomainInformation = shopProductQueryDao.findByShopIdAndProductId(shopId, productId);
        final ProductHttpResponse productHttpResponse = new ProductHttpResponse(
                productDomainInformation.getId(),
                productDomainInformation.getShopId(),
                productDomainInformation.getProductName(),
                productDomainInformation.getProductDescription(),
                productDomainInformation.getProductOptionGroups().stream()
                        .map(productOptionGroupInformation -> new ProductOptionGroupHttpResponse(
                                productOptionGroupInformation.getProductOptionGroupId(),
                                productOptionGroupInformation.getProductOptionGroupName(),
                                productOptionGroupInformation.getProductOptions().stream()
                                        .map(productOptionInformation -> new ProductOptionHttpResponse(
                                                productOptionInformation.getProductOptionId(),
                                                productOptionInformation.getProductOptionName(),
                                                productOptionInformation.getProductPrice(),
                                                productOptionInformation.getCreatedAt(),
                                                productOptionInformation.getUpdatedAt()
                                        ))
                                        .toList(),
                                productOptionGroupInformation.getCreatedAt(),
                                productOptionGroupInformation.getUpdatedAt()
                        ))
                        .toList(),
                productDomainInformation.getCreatedAt(),
                productDomainInformation.getUpdatedAt()
        );
        return ResponseEntity.ok().body(productHttpResponse);
    }

    private List<ProductInfoHttpResponse> getProductInfo(final List<ShopProductInformation> shopProductInformationList) {
        return shopProductInformationList.stream()
                .map(shopProductInformation -> new ProductInfoHttpResponse(
                        shopProductInformation.productId(),
                        shopProductInformation.productName(),
                        shopProductInformation.productDescription()))
                .toList();
    }

    private ShopInfoHttpResponse getShopInfo(final List<ShopProductInformation> shopProductInformationList) {
        return new ShopInfoHttpResponse(
                shopProductInformationList.getFirst().shopId(),
                shopProductInformationList.getFirst().shopName(),
                shopProductInformationList.getFirst().shopDescription()
        );
    }
}
