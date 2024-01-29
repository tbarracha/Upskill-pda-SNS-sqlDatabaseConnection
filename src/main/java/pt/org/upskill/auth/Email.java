package pt.org.upskill.auth;

import pt.org.upskill.dto.DTOable;
import pt.org.upskill.dto.EmailDTO;

public class Email implements DTOable {
    private String address;

    public Email(String address) throws Exception {
        if (!validate(address)) {
            throw new Exception("Invalid email address: " + address);
        }
        this.address = address;
    }

    private boolean validate(String email) {
        return email.contains("@");
    }

    public String address() {
        return address;
    }

    @Override
    public Object toDTO() {
        return new EmailDTO(address());
    }
}
