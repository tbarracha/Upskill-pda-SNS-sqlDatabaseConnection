package pt.org.upskill;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.session.Context;

public class Main {

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();

        Context.getInstance().drawer().draw(Context.getInstance().mainMenu());
    }
}