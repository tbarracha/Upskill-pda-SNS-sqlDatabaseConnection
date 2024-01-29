package pt.org.upskill.domain;

import pt.org.upskill.dto.*;

public class Vaccine implements DTOable<VaccineDTO> {
    private Integer id;
    private String name;
    private VaccineType vaccineType;
    private Brand brand;
/*
    public Vaccine(Integer id, String name, VaccineType vaccineType, Brand brand) {
        this.id = id;
        this.name = name;
        this.vaccineType = vaccineType;
        this.brand = brand;
    }
 */
    private Vaccine(final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.vaccineType = builder.vaccineType;
        this.brand = builder.brand;
    }

    public Integer id() {
        return this.id;
    }
    public String name() {
        return name;
    }
    public VaccineType vaccineType() {
        return vaccineType;
    }
    public Brand brand() {
        return this.brand;
    }

    @Override
    public VaccineDTO toDTO() {
        VaccineDTO dto = new VaccineDTO.Builder()
                .withId(id())
                .withName(name())
                .withVaccineTypeDTO(vaccineType().toDTO())
                .withBrandDTO(brand().toDTO())
                .build();
        return dto;
    }

    public static class Builder {

        private Integer id;
        private String name;
        private VaccineType vaccineType;
        private Brand brand;

        public Builder withId(final Integer id) {
            this.id = id;
            return this;
        }
        public Builder withName(final String name) {
            this.name = name;
            return this;
        }
        public Builder withVaccineType(final VaccineType vaccineType) {
            this.vaccineType = vaccineType;
            return this;
        }
        public Builder withBrand(final Brand brand) {
            this.brand = brand;
            return this;
        }

        public Vaccine build() {
            return new Vaccine(this);
        }
    }
}
