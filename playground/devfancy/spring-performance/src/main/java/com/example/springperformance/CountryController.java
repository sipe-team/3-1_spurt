package com.example.springperformance;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CountryController {

    private final CountryService countryService;

    public CountryController(final CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/external/countries")
    public ResponseEntity<String> getCountries() {
        return countryService.getCountries();
    }
}
