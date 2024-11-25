package team.sipe.commerce.shop.shop.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team.sipe.commerce.shop.shop.api.dto.ShopRegisterHttpRequest;
import team.sipe.commerce.shop.shop.api.dto.ShopRegisterHttpResponse;
import team.sipe.commerce.shop.shop.application.ShopRegisterUseCase;

import java.net.URI;

@RestController
public class ShopApi {

    private final ShopRegisterUseCase shopRegisterUseCase;

    public ShopApi(final ShopRegisterUseCase shopRegisterUseCase) {
        this.shopRegisterUseCase = shopRegisterUseCase;
    }

    @PostMapping("/shops")
    public ResponseEntity<Object> registerShop(@RequestBody final ShopRegisterHttpRequest shopRegisterHttpRequest) {
        final Long id = shopRegisterUseCase.register(shopRegisterHttpRequest.toCommand());
        return ResponseEntity.created(URI.create("/shops/" + id)).body(new ShopRegisterHttpResponse(id));
    }
}
