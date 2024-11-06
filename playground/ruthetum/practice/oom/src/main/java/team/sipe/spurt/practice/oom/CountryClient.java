package team.sipe.spurt.practice.oom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class CountryClient {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final TypeFactory typeFactory = objectMapper.getTypeFactory();

    private static final String API_URL = "https://gist.githubusercontent.com/anubhavshrimal/75f6183458db8c453306f93521e93d37/raw/f77e7598a8503f1f70528ae1cbf9f66755698a16/CountryCodes.json";

    public List<ExternalCountryResponse> getExternalCountry() {
        try {
            var client = new OkHttpClient();
            var request = new Request.Builder().url(API_URL).build();
            var response = client.newCall(request).execute().body();
            if (response == null) {
                return List.of();
            }
            return objectMapper.readValue(response.string(), typeFactory.constructCollectionType(List.class, ExternalCountryResponse.class));
        } catch (IOException e) {
//            log.warn("Error fetching response");
            return List.of();
        }
    }
}
