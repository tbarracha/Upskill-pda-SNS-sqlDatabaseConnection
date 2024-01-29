package pt.org.upskill.session;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.auth.User;
import pt.org.upskill.domain.Facility;

public class Session {
    User user;
    Facility facility;

    public Session(User user) {
        this.user = user;
    }
    public Session(User user, Facility facility) {
        this.user = user;
        this.facility = facility;
    }

    public User user() {
        return this.user;
    }
    public Facility facility() {
        return this.facility;
    }
}
