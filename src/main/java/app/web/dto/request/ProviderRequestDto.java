package app.web.dto.request;

import com.neovisionaries.i18n.CountryCode;

public class ProviderRequestDto extends RequestDto{

    private String name;

    private CountryCode country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryCode getCountry() {
        return country;
    }

    public void setCountry(CountryCode country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "ProviderRequestDto{" +
                "name='" + name + '\'' +
                ", country=" + country +
                '}';
    }
}
