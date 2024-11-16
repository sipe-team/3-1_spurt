package com.example.springperformance;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private CountryClient countryClient;

    public CountryService(CountryClient countryClient) {
        this.countryClient = countryClient;
    }

    public List<CountryResponse> getExternalCountry() {
        return countryClient.getExternalCountry()
                .stream()
                .map(CountryResponse::fromExternal)
                .collect(Collectors.toList());
    }
}
