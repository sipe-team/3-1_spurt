package com.poc.performance.api;

import com.poc.performance.application.CountryService;
import com.poc.performance.domain.Countries;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryQueryApi {

    private final CountryService countryService;

    public CountryQueryApi(final CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/external/countries")
    public ResponseEntity<Countries> countries() {
        return ResponseEntity.ok().body(countryService.countries());
    }
}

