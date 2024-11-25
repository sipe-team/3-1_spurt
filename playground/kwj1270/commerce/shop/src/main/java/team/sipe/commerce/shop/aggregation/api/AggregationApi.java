package team.sipe.commerce.shop.aggregation.api;

import org.jooq.generated.tables.pojos.Shops;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import team.sipe.commerce.shop.aggregation.api.dto.ShopInformationResponse;
import team.sipe.commerce.shop.aggregation.dao.ShopQueryDao;

@RestController
public class AggregationApi {

    private final ShopQueryDao shopQueryDao;

    public AggregationApi(final ShopQueryDao shopQueryDao) {
        this.shopQueryDao = shopQueryDao;
    }

    @GetMapping("/shops/{shopId}")
    public ResponseEntity<ShopInformationResponse> findShop(@PathVariable("shopId") final Long shopId) {
        final Shops shops = shopQueryDao.findShopById(shopId);
        final ShopInformationResponse shopInformationResponse = new ShopInformationResponse(shops.getId(), shops.getSellerId(), shops.getName(), shops.getDescription(), shops.getAddress(), shops.getCreatedAt(), shops.getUpdatedAt());
        return ResponseEntity.ok().body(shopInformationResponse);
    }
}
