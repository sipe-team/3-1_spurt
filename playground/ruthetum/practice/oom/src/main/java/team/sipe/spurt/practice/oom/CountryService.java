package team.sipe.spurt.practice.oom;

import org.springframework.stereotype.Service;

import java.util.List;

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
