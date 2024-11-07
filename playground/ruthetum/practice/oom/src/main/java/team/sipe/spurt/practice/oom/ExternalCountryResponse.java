package team.sipe.spurt.practice.oom;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalCountryResponse {

    private String name;
    private String code;
    @JsonProperty("dial_code")
    private String dialCode;

    public ExternalCountryResponse() { }

    public ExternalCountryResponse(String name, String code, String dialCode) {
        this.name = name;
        this.code = code;
        this.dialCode = dialCode;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getDialCode() {
        return dialCode;
    }
}
