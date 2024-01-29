package pt.org.upskill.auth;

public class Password {
    private String password;

    public Password(String pwd) {
        if (!validate(pwd)) {
            new Exception("Invalid password");
        }
        this.password = pwd;
    }

    private boolean validate(String pwd) {
        return true;
    }

    public String value() {
        return password;
    }
}
