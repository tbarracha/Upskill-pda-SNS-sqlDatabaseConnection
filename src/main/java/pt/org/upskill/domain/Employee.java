package pt.org.upskill.domain;

import pt.org.upskill.auth.Email;
import pt.org.upskill.dto.BrandDTO;

public class Employee {
    private final Email email;
    private String name;
    private String position;
    private String phone;

    public Employee(Email email, String name) {
        this.email = email;
        this.name = name;
    }

    public Employee(Email email, String name, String position, String phone) {
        this.email = email;
        this.name = name;
        this.position = position;
        this.phone = phone;
    }


    public Email getEmail() {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return email.equals(employee.email);
    }

    public boolean hasEmail(String email) {
        return this.email.address().equals(email);
    }

}