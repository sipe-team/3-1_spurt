package com.poc.performance.application;

import com.poc.performance.domain.Countries;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    private final CountryClient countryClient;

    public CountryService(final CountryClient countryClient) {
        this.countryClient = countryClient;
    }

    public Countries countries() {
        return countryClient.countries();
    }
}
