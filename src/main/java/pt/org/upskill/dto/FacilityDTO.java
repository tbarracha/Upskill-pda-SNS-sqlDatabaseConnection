package pt.org.upskill.dto;

import java.time.LocalTime;

public class FacilityDTO implements DTO {
    private Integer id;
    private String name;
    private AddressDTO addressDTO;
    private PhoneDTO phoneDTO;
    private EmailDTO emailDTO;
    private PhoneDTO faxDTO;
    private WebsiteDTO websiteDTO;
    private LocalTime openingHour;
    private LocalTime closingHour;
    private int maxVaccinesPerHour;

    public Integer id() {
        return this.id;
    }

    public String name() {
        return this.name;
    }

    public AddressDTO addressDTO() {
        return this.addressDTO;
    }

    public PhoneDTO phoneDTO() {
        return this.phoneDTO;
    }

    public EmailDTO emailDTO() {
        return this.emailDTO;
    }

    public PhoneDTO faxDTO() {
        return this.faxDTO;
    }

    public WebsiteDTO websiteDTO() {
        return this.websiteDTO;
    }

    public LocalTime openingHour() {
        return this.openingHour;
    }

    public LocalTime closingHour() {
        return this.closingHour;
    }

    public Integer maxVaccinesPerHour() {
        return this.maxVaccinesPerHour;
    }

    private FacilityDTO(final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.addressDTO = builder.addressDTO;
        this.phoneDTO = builder.phoneDTO;
        this.emailDTO = builder.emailDTO;
        this.faxDTO = builder.faxDTO;
        this.websiteDTO = builder.websiteDTO;
        this.openingHour = builder.openingHour;
        this.closingHour = builder.closingHour;
        this.maxVaccinesPerHour = builder.maxVaccinesPerHour;
    }

    public static class Builder {
        private Integer id;
        private String name;
        private AddressDTO addressDTO;
        private PhoneDTO phoneDTO;
        private EmailDTO emailDTO;
        private PhoneDTO faxDTO;
        private WebsiteDTO websiteDTO;
        private LocalTime openingHour;
        private LocalTime closingHour;
        private int maxVaccinesPerHour;

        public Builder withId(final Integer id) {
            this.id = id;
            return this;
        }
        public Builder withName(final String name) {
            this.name = name;
            return this;
        }
        public Builder withAddressDTO(final AddressDTO addressDTO) {
            this.addressDTO = addressDTO;
            return this;
        }
        public Builder withPhoneDTO(final PhoneDTO phoneDTO) {
            this.phoneDTO = phoneDTO;
            return this;
        }
        public Builder withEmailDTO(final EmailDTO emailDTO) {
            this.emailDTO = emailDTO;
            return this;
        }
        public Builder withFaxDTO(final PhoneDTO faxDTO) {
            this.faxDTO = faxDTO;
            return this;
        }
        public Builder withWebsiteDTO(final WebsiteDTO websiteDTO) {
            this.websiteDTO = websiteDTO;
            return this;
        }
        public Builder withOpeningHour(final LocalTime openingHour) {
            this.openingHour = openingHour;
            return this;
        }
        public Builder withClosingingHour(final LocalTime closingHour) {
            this.closingHour = closingHour;
            return this;
        }
        public Builder withMaxVaccinesPerHour(final Integer maxVaccinesPerHour) {
            this.maxVaccinesPerHour = maxVaccinesPerHour;
            return this;
        }

        public FacilityDTO build() {
            return new FacilityDTO(this);
        }
    }
}