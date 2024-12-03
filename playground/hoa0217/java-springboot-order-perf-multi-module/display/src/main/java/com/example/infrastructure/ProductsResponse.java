package com.example.infrastructure;

import com.example.domain.Product;
import java.util.List;

public record ProductsResponse(
        List<Product> products
) {

}
