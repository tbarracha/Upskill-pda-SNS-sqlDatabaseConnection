package pt.org.upskill.dto;

public class VaccineDTO implements DTO {
    private Integer id;
    private String name;
    private VaccineTypeDTO vaccineTypeDTO;
    private BrandDTO brandDTO;

    public Integer id() {
        return this.id;
    }
    public String name() {
        return this.name;
    }
    public VaccineTypeDTO vaccineTypeDTO() {
        return this.vaccineTypeDTO;
    }
    public BrandDTO brandDTO() {
        return this.brandDTO;
    }

    private VaccineDTO(final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.vaccineTypeDTO = builder.vaccineTypeDTO;;
        this.brandDTO = builder.brandDTO;
    }

    public static class Builder {
        private Integer id;
        private String name;
        private VaccineTypeDTO vaccineTypeDTO;
        private BrandDTO brandDTO;

        public Builder withId(final Integer id) {
            this.id = id;
            return this;
        }
        public Builder withName(final String name) {
            this.name = name;
            return this;
        }
        public Builder withVaccineTypeDTO(final VaccineTypeDTO vaccineTypeDTO) {
            this.vaccineTypeDTO = vaccineTypeDTO;
            return this;
        }
        public Builder withBrandDTO(final BrandDTO brandDTO) {
            this.brandDTO = brandDTO;
            return this;
        }

        public VaccineDTO build() {
            return new VaccineDTO(this);
        }
    }
}
