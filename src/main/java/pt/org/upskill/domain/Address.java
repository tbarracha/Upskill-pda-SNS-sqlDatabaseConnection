package pt.org.upskill.domain;

import pt.org.upskill.dto.AddressDTO;
import pt.org.upskill.dto.DTOable;

public class Address implements DTOable<AddressDTO> {
    private String streetName;
    private String postalCode;
    private String cityName;

    public String streetName() {
        return this.streetName;
    }
    public String postalCode() {
        return this.postalCode;
    }
    public String cityName() {
        return this.cityName;
    }

    public Address(String streetName, String postalCode, String cityName) {
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.cityName = cityName;
    }

    @Override
    public AddressDTO toDTO() {
        AddressDTO.Builder builder = new AddressDTO.Builder();
        if (streetName() != null) {
            builder.withStreetName(streetName());
        }
        if (postalCode() != null) {
            builder.withPostalCode(postalCode());
        }
        if (cityName() != null) {
            builder.withCityName(cityName());
        }
        return builder.build();
    }
}
