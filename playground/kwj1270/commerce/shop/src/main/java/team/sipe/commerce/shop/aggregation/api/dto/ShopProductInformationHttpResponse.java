package team.sipe.commerce.shop.aggregation.api.dto;

import java.util.List;

public record ShopProductInformationHttpResponse(
        ShopInfoHttpResponse shopInfo,
        List<ProductInfoHttpResponse> productInfos
) {
}

