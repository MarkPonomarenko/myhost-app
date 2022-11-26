package app.web.dto.response;

import com.neovisionaries.i18n.CountryCode;
import app.persistence.entity.provider.Provider;

public class ProviderResponseDto extends ResponseDto{

    private String name;

    private CountryCode country;

    public ProviderResponseDto() {

    }

    public ProviderResponseDto(Provider provider) {
        setId(provider.getId());
        setCreated(provider.getCreated());
        setUpdated(provider.getUpdated());
        this.name = provider.getName();
        this.country = provider.getCountry();
    }

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
        return "ProviderResponseDto{" +
                "name='" + name + '\'' +
                ", country=" + country +
                '}';
    }
}
