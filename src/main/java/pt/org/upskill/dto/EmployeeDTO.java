package pt.org.upskill.dto;

import pt.org.upskill.auth.Email;

public class EmployeeDTO implements DTO  {
    private String email;
    private String name;
    private String position;
    private String phone;

    public EmployeeDTO(String email, String name, String position, String phone) {
        this.email = email;
        this.name = name;
        this.position = position;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
