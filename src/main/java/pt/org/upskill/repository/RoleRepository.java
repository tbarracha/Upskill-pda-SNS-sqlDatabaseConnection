package pt.org.upskill.repository;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.domain.Role;

import java.util.ArrayList;
import java.util.List;


public class RoleRepository {
    public static final String ROLE_ADMIN = "ADMINISTRATOR";
    public static final String ROLE_NURSE = "NURSE";
    public static final String ROLE_SNSUSER = "SNSUSER";
    public static final String ROLE_RECEPTIONIST = "RECEPTIONIST";

    private static final List<Role> roles = new ArrayList<>();

    public boolean add(Role role) {
        try {
            roles.add(role);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Role roleByName(String name) {
        for (Role role : roles) {
            if (role.name() == name) {
                return role;
            };
        }
        return null;
    }
}
