package pt.org.upskill.dto;


public class VaccineTypeDTO implements DTO {
    private String code;
    private String shortDescription;
    private VaccineTechDTO vaccineTechDTO;

    public String code() {
        return this.code;
    }
    public String shortDescription() {
        return this.shortDescription;
    }
    public VaccineTechDTO vaccineTechDTO() {
        return this.vaccineTechDTO;
    }

    private VaccineTypeDTO(final Builder builder) {
        this.code = builder.code;
        this.shortDescription = builder.shortDescription;;
        this.vaccineTechDTO = builder.vaccineTechDTO;
    }

    public static class Builder {
        private String code;
        private String shortDescription;
        private VaccineTechDTO vaccineTechDTO;

        public Builder withCode(final String code) {
            this.code = code;
            return this;
        }
        public Builder withShortDescription(final String shortDescription) {
            this.shortDescription = shortDescription;
            return this;
        }
        public Builder withVaccineTechDTO(final VaccineTechDTO vaccineTechDTO) {
            this.vaccineTechDTO = vaccineTechDTO;
            return this;
        }

        public VaccineTypeDTO build() {
            return new VaccineTypeDTO(this);
        }
    }
}
