package pt.org.upskill.dto;

public class EmailDTO implements DTO {
    private String address;

    public String address() {
        return this.address;
    }

    public EmailDTO(String address) {
        this.address = address;
    }
}
