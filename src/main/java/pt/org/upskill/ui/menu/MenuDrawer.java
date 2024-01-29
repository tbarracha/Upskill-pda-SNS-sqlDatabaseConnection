package pt.org.upskill.ui.menu;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.auth.User;
import pt.org.upskill.session.Context;
import pt.org.upskill.session.Session;
import pt.org.upskill.ui.utils.Utils;

import java.util.Iterator;

public class MenuDrawer implements Drawable {

    private User userInSession() {
        Session session = Context.getInstance().session();
        if (session != null) {
            return session.user();
        }
        return null;
    }

    public void draw(Menu menu) {
        if (menu == null) {
            System.exit(0); // Exit with status code 0 (normal exit)
        }

        //Menu header
        System.out.println("");
        System.out.println(menu.caption());
        //Items
        Iterator<Menu> itr = menu.menuItems(Context.getInstance().userLoggedIn()).iterator();
        while (itr.hasNext()) {
            Menu item = itr.next();
            System.out.println(item.option() + " " + item.caption());
        }
        System.out.println("0 Back");

        //User input
        String input;
        Integer value;
        do {
            input = Utils.readLineFromConsole("Type your option: ");
            try {
                value = Integer.valueOf(input);
                if (value == 0) break;
            } catch (NumberFormatException ex) {
                value = -1;
            }
        } while (value < 0 || (!menu.isValidMenuItemOption(value)));

        //Draws the menu
        switch (value) {
            case 0 :
                draw(menu.parent());
                break;
            default:
                //Execute the action associated to the selected menu
                Menu selectedMenu = menu.menuItem(value);
                selectedMenu.execute();
                //Obtains the next menu to draw
                if (selectedMenu.isFinal()) {
                    draw(selectedMenu.parent());
                } else {
                    draw(selectedMenu);
                }
                break;
        }
    }
}
