package com.poc.performance.infrastructure.mapper;

import com.poc.performance.domain.Country;
import com.poc.performance.infrastructure.dto.ExternalCountryResponse;

public class CountryMapper {
    private CountryMapper() {
    }

    public static Country toDomain(final ExternalCountryResponse externalCountryResponse) {
        return new Country(null, externalCountryResponse.name(), externalCountryResponse.code(), externalCountryResponse.dialCode());
    }
}
