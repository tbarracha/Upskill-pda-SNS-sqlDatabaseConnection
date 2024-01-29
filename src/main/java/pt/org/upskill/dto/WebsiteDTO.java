package pt.org.upskill.dto;

public class WebsiteDTO implements DTO {
    private String address;

    public String address() {
        return this.address;
    }

    public WebsiteDTO(String address) {
        this.address = address;
    }

}
