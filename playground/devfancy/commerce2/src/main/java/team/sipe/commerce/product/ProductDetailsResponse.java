package team.sipe.commerce.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

// 상품 상세 정보 DTO
@Getter
public class ProductDetailsResponse {

    private final Long productId;
    private final String name;
    private final String description;
    private final int price;

    @JsonCreator
    @Builder
    public ProductDetailsResponse(@JsonProperty("productId") final Long productId,
                                  @JsonProperty("name") final String name,
                                  @JsonProperty("description") final String description,
                                  @JsonProperty("price") final int price) {

        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public static ProductDetailsResponse from(final Product product) {
        return ProductDetailsResponse.builder()
                .productId(product.getId())
                .name(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
