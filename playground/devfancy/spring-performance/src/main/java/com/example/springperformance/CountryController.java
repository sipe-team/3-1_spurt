package com.example.springperformance;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {

    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/api/external/countries")
    public ResponseEntity<List<CountryResponse>> getCountries() {
        return ResponseEntity.ok(countryService.getExternalCountry());
    }
}
