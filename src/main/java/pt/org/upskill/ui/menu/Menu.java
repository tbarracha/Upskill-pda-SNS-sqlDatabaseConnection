package pt.org.upskill.ui.menu;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.auth.User;
import pt.org.upskill.domain.Role;
import pt.org.upskill.session.Context;
import pt.org.upskill.ui.UI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Menu {
    private Menu parentMenu;

    private int option;
    private String text;
    private UI ui;

    private List<Menu> menuItems = new ArrayList<>();
    private List<Permission> menuPermissions = new ArrayList<>();

    public Menu(Menu parentMenu, int option, String text, UI ui) {
        this.parentMenu = parentMenu;
        this.option = option;
        this.text = text;
        this.ui = ui;

        //The menu is added to the parent
        if (this.parentMenu != null) {
            this.parentMenu.addMenuItem(this);
        }
    }

    private void addMenuItem(Menu item) {
        menuItems.add(item);
    }

    public List<Menu> menuItems(User user) {
        //This returns all the menu items for a specific user
        //If the user is not assigned it can only return the main menu items
        //If permissions are not assigned to the menu item it means that the menu item is visible for all
        List<Menu> ret = new ArrayList<>();
        //
        Iterator<Menu> itr = menuItems.iterator();
        while (itr.hasNext()) {
            Menu menu = itr.next();
            if (menu.isAllowedTo(user)) {
                ret.add(menu);
            }
        }
        return ret;
    }

    public void addPermission(Role role) {
        menuPermissions.add(new Permission(role));
    }

    public int option() {
        return this.option;
    }

    public String caption() {
        return this.text;
    }

    public boolean isValidMenuItemOption(int option) {
        Iterator<Menu> itr = menuItems(Context.getInstance().userLoggedIn()).iterator();
        while (itr.hasNext()) {
            Menu item = itr.next();
            if (item.option() == option) {
                return true;
            };
        }
        return false;
    }

    public Menu menuItem(int option) {
        Iterator<Menu> itr = menuItems(Context.getInstance().userLoggedIn()).iterator();
        while (itr.hasNext()) {
            Menu item = itr.next();
            if (item.option() == option) {
                return item;
            };
        }
        return null;
    }

    public Menu parent() {
        return parentMenu;
    }

    public void execute() {
        if (ui != null) {
            ui.run();
        }
    }

    public boolean isAllowedTo(User user) {
        if (this.parentMenu == Context.getInstance().mainMenu()) {
            return true; //Main menu items are always visible
        }
        if (user == null) {
            return false; //No user no menus
        }

        Iterator<Permission> itr = menuPermissions.iterator();
        while (itr.hasNext()) {
            Permission element = itr.next();
            if (user.hasRole(element.role())) {
                return true;
            }
        }
        return false;
    }

    public boolean isFinal() {
        return menuItems(Context.getInstance().userLoggedIn()).size() == 0;
    }
}
