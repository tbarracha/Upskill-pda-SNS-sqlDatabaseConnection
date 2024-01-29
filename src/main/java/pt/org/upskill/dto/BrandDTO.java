package pt.org.upskill.dto;

public class BrandDTO implements DTO {
    private String name;

    public String name() {
        return this.name;
    }

    private BrandDTO(final Builder builder) {
        this.name = builder.name;;
    }

    public static class Builder {
        private String name;

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public BrandDTO build() {
            return new BrandDTO(this);
        }
    }
}
