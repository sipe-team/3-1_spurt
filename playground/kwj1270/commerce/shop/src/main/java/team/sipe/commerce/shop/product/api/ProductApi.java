package team.sipe.commerce.shop.product.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.sipe.commerce.shop.product.api.dto.ProductRegisterHttpRequest;
import team.sipe.commerce.shop.product.api.dto.ProductRegisterHttpResponse;
import team.sipe.commerce.shop.product.application.ProductRegisterUseCase;

@RequestMapping("/shops/{shopId}/products")
@RestController
public class ProductApi {

    private final ProductRegisterUseCase productRegisterUseCase;

    public ProductApi(final ProductRegisterUseCase productRegisterUseCase) {
        this.productRegisterUseCase = productRegisterUseCase;
    }

    @PostMapping
    public ResponseEntity<ProductRegisterHttpResponse> registerProduct(@PathVariable("shopId") final Long shopId,
                                                                       @RequestBody final ProductRegisterHttpRequest request) {
        final Long productId = productRegisterUseCase.register(request.toCommand(shopId));
        return ResponseEntity.ok().body(new ProductRegisterHttpResponse(productId));
    }

}
