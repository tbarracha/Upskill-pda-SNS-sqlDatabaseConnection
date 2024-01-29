package pt.org.upskill.domain;

import pt.org.upskill.dto.DTOable;
import pt.org.upskill.dto.PhoneDTO;

public class Phone implements DTOable<PhoneDTO> {
    private String phoneNumber;

    public String phoneNumber() {
        return this.phoneNumber;
    }
    public Phone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public PhoneDTO toDTO() {
        return new PhoneDTO(phoneNumber());
    }
}
