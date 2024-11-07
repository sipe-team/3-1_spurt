package team.sipe.spurt.practice.oom;

public class CountryResponse {

    private Long id;
    private String name;
    private String code;
    private String dialCode;

    public CountryResponse() { }

    public CountryResponse(Long id, String name, String code, String dialCode) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.dialCode = dialCode;
    }

    public Long getId() {
        return id;
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

    public static CountryResponse fromExternal(ExternalCountryResponse externalCountry) {
        return new CountryResponse(null, externalCountry.getName(), externalCountry.getCode(), externalCountry.getDialCode());
    }
}
