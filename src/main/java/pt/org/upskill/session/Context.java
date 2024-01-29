package pt.org.upskill.session;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.auth.User;
import pt.org.upskill.db.ConnectionFactory;
import pt.org.upskill.ui.menu.Drawable;
import pt.org.upskill.ui.menu.Menu;

public class Context {

    public static Context getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final Context INSTANCE = new Context();
    }

    //Main Menu
    private Menu mainMenu;

    public void setMainMenu(Menu menu) {
        this.mainMenu = menu;
    };

    public Menu mainMenu() {
        return mainMenu;
    }

    //Menu Drawer
    private Drawable drawer;

    public void setDrawer(Drawable drawer) {
        this.drawer = drawer;
    };

    public Drawable drawer() {
        return drawer;
    }

    //Session
    private Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    public Session session() {
        return session;
    }

    public User userLoggedIn() {
        if (session() != null) {
            return session.user();
        }
        return null;
    }

    //Connection Factory
    private static ConnectionFactory cf = new ConnectionFactory();

    public static ConnectionFactory getConnectionFactory() {
        return cf;
    }
}