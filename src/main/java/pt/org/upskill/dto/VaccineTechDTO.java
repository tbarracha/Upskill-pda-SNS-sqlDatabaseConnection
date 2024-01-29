package pt.org.upskill.dto;

public class VaccineTechDTO implements DTO {
    private Integer id;
    private String name;
    private String description;

    public Integer id() {
        return this.id;
    }
    public String name() {
        return this.name;
    }
    public  String description() {
        return this.description;
    }

    private VaccineTechDTO(final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;;
        this.description = builder.description;;
    }

    public static class Builder {
        private Integer id;
        private String name;
        private String description;

        public Builder withId(final Integer id) {
            this.id = id;
            return this;
        }
        public Builder withName(final String name) {
            this.name = name;
            return this;
        }
        public Builder withDescription(final String description) {
            this.description = description;
            return this;
        }

        public VaccineTechDTO build() {
            return new VaccineTechDTO(this);
        }
    }
}