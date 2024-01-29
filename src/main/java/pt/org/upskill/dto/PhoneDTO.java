package pt.org.upskill.dto;

public class PhoneDTO implements DTO {
    private String phoneNumber;

    public String phoneNumber() {
        return phoneNumber;
    }

    public PhoneDTO(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
