package com.poc.performance;

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
                .toList();
    }
}