package pt.org.upskill.dto;

public class AddressDTO implements DTO {
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

    private AddressDTO(final Builder builder) {
        this.streetName = builder.streetName;;
        this.postalCode = builder.postalCode;
        this.cityName = builder.cityName;
    }

    public static class Builder {
        private String streetName;
        private String postalCode;
        private String cityName;

        public Builder withStreetName(final String streetName) {
            this.streetName = streetName;
            return this;
        }
        public Builder withPostalCode(final String postalCode) {
            this.postalCode = postalCode;
            return this;
        }
        public Builder withCityName(final String cityName) {
            this.cityName = cityName;
            return this;
        }

        public AddressDTO build() {
            return new AddressDTO(this);
        }
    }
}
