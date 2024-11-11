package com.poc.performance;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryQueryApi {

    @GetMapping("/external/countries")
    public ResponseEntity<List<CountryResponse>> getCountries() {
        return ResponseEntity.ok("Countries");
    }
}

