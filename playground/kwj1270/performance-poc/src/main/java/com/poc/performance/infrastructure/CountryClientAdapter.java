package com.poc.performance.infrastructure;


import com.poc.performance.application.CountryClient;
import com.poc.performance.domain.Countries;
import com.poc.performance.infrastructure.mapper.CountryMapper;
import org.springframework.stereotype.Component;

@Component
public class CountryClientAdapter implements CountryClient {

    private final CountryOkHttpClient countryOkHttpClient;

    public CountryClientAdapter(final CountryOkHttpClient countryOkHttpClient) {
        this.countryOkHttpClient = countryOkHttpClient;
    }

    @Override
    public Countries countries() {
        return new Countries(countryOkHttpClient.externalCountries().stream()
                .map(CountryMapper::toDomain)
                .toList());
    }
}
