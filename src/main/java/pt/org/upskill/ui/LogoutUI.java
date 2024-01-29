package pt.org.upskill.ui;

import pt.org.upskill.controller.LoginController;
import pt.org.upskill.session.Context;

public class LogoutUI extends UI {

    //Controller
    private final LoginController controller = new LoginController();

    private String email;
    private String password;

    public void run() {
        System.out.println("");
        System.out.println("LOG OUT");
        System.out.println("-------");

        if (Context.getInstance().session() == null) {
            System.out.print("Not logged in...");
        } else {
            controller.logOut(Context.getInstance().session().user());
            System.out.print("Logged out");
        }
    }
}
