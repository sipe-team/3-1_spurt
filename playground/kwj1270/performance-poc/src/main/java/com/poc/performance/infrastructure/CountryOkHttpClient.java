package com.poc.performance.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.poc.performance.infrastructure.dto.ExternalCountryResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component
public class CountryOkHttpClient {

    private static final String API_URL = "https://gist.githubusercontent.com/anubhavshrimal/75f6183458db8c453306f93521e93d37/raw/f77e7598a8503f1f70528ae1cbf9f66755698a16/CountryCodes.json";

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final TypeFactory typeFactory = objectMapper.getTypeFactory();

    public List<ExternalCountryResponse> externalCountries() {
        try {
            var client = new OkHttpClient();
            var request = new Request.Builder().url(API_URL).build();
            var response = client.newCall(request).execute().body();
            if (Objects.isNull(response)) {
                return List.of();
            }
            return objectMapper.readValue(response.string(), typeFactory.constructCollectionType(List.class, ExternalCountryResponse.class));
        } catch (IOException e) {
            return List.of();
        }
    }
}