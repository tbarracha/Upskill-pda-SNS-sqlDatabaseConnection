package pt.org.upskill.auth;

import pt.org.upskill.domain.Role;

public class User {
    private String code;
    private Email email;
    private Password password;
    private Role role;

    public User(String code, Role role, Email email, Password password) {
        if (role == null) {
            throw new IllegalArgumentException("User role cannot be null.");
        }
        this.code = code;
        if (code == null) {
            this.code = email.address();
        }
        this.role = role;
        this.email = email;
        this.password = password;
    }

    protected User() {
    }

    public String code() {
        return code;
    }

    public Email email() {
        return this.email;
    }

    public Password password() {
        return this.password;
    }

    public boolean hasRole(Role role) {
        if (role == null) {
            return false;
        }
        return this.role.name().equalsIgnoreCase(role.name());
    }

    public boolean hasPassword(String password) {
        return this.password.value().equals(password);
    }
}
