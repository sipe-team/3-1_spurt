package com.example.springperformance;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Transactional(readOnly = true)
@Service
public class CountryService {
    private static final String COUNTRY_CODES_URL = "https://gist.githubusercontent.com/anubhavshrimal/75f6183458db8c453306f93521e93d37/raw/f77e7598a8503f1f70528ae1cbf9f66755698a16/CountryCodes.json";

    public ResponseEntity<String> getCountries() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(COUNTRY_CODES_URL, String.class);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching country data");
        }
    }
}
