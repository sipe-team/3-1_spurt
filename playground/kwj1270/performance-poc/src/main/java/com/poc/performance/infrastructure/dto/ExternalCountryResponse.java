package com.poc.performance;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ExternalCountryResponse(
        String name,
        String code,
        @JsonProperty("dial_code") String dialCode
) {
}