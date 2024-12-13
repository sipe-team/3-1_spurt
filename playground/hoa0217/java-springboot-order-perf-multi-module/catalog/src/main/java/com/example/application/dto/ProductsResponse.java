package com.example.application.dto;

import java.util.List;

public record ProductsResponse(
        List<ProductResponse> products
) {

}
