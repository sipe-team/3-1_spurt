package team.sipe.commerce.shop.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/shop/product")
@RestController
public class FakeProductApi {

    @GetMapping("/{productId}")
    public ResponseEntity<ProductInfoHttpResponse> productInfo(@PathVariable final String productId) {
        return ResponseEntity.ok(new ProductInfoHttpResponse());
    }

    record ProductInfoHttpResponse(
            long productId,
            String name,
            int price
    ) {
        public ProductInfoHttpResponse() {
            this(1, "Product 1", 100);
        }
    }


}
