package team.sipe.commerce.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

// 상품 묶음 관련 정보 DTO
@Getter
public class ProductBundleDetailsResponse {

    private final String bundleName;
    private final int bundleQuantity;

    @JsonCreator
    public ProductBundleDetailsResponse(@JsonProperty("bundleName") final String bundleName,
                                        @JsonProperty("bundleQuantity") final int bundleQuantity) {
        this.bundleName = bundleName;
        this.bundleQuantity = bundleQuantity;
    }
}
