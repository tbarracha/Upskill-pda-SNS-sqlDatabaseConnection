package pt.org.upskill;
/**
 * @author Nuno Castro anc@isep.ipp.pt
 */

import pt.org.upskill.auth.Email;
import pt.org.upskill.auth.Password;
import pt.org.upskill.auth.User;
import pt.org.upskill.domain.*;
import pt.org.upskill.repository.*;
import pt.org.upskill.session.Context;
import pt.org.upskill.ui.*;
import pt.org.upskill.ui.menu.Drawable;
import pt.org.upskill.ui.menu.Menu;
import pt.org.upskill.ui.menu.MenuDrawer;

import java.time.LocalTime;

import static pt.org.upskill.repository.RoleRepository.*;

public class Bootstrap implements Runnable {

    //Add some task categories to the repository as bootstrap
    public void run() {
        addRoles();
        addUsers();
        addMenus();
        addFacilities();
        addEmployees();
    }

    private void addRoles() {
        //TODO: add application user roles here
        RoleRepository roleRepository = Repositories.getInstance().roleRepository();

        roleRepository.add(new Role(ROLE_ADMIN));
        roleRepository.add(new Role(ROLE_NURSE));
        roleRepository.add(new Role(ROLE_RECEPTIONIST));
        roleRepository.add(new Role(ROLE_SNSUSER));
    }

    private void addUsers() {
        //TODO: add Authentication users here: should be created for each user in the organization
        UserRepository userRepository = Repositories.getInstance().userRepository();
        RoleRepository roleRepository = Repositories.getInstance().roleRepository();

        try {
            userRepository.add(new User("adm", roleRepository.roleByName(ROLE_ADMIN), new Email("admin@upskill.pt"), new Password("admin")));
            userRepository.add(new User("nur", roleRepository.roleByName(ROLE_NURSE), new Email("nurse@upskill.pt"), new Password("nurse")));
            userRepository.add(new User("rec", roleRepository.roleByName(ROLE_RECEPTIONIST), new Email("receptionist@upskill.pt"), new Password("receptionist")));
            userRepository.add(new User("usr", roleRepository.roleByName(ROLE_SNSUSER), new Email("snsuser@upskill.pt"), new Password("snsuser")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addFacilities() {
        FacilityRepository facilityRepository = Repositories.getInstance().facilityRepository();

        try {
            Facility f1 = new Facility.Builder()
                    .withId(1)
                    .withName("Centro de Saúde de Amarante")
                    .withAddress(new Address("Rua X", "4600-011", "Amarante"))
                    .withPhone(new Phone("255 123 456"))
                    .withEmail(new Email("csa@csa.pt"))
                    .withOpeningHour(LocalTime.of(9,0))
                    .withClosingingHour(LocalTime.of(19,30))
                    .withMaxVaccinesPerHour(120)
                    .build();
            Facility f2 = new Facility.Builder()
                    .withId(2)
                    .withName("Centro de Saúde do Porto")
                    .withAddress(new Address("Rua da saúde", "4000-311", "Porto"))
                    .build();
            Facility f3 = new Facility.Builder()
                    .withId(3)
                    .withName("Centro de Saúde de Braga")
                    .build();

            facilityRepository.save(f1);
            facilityRepository.save(f2);
            facilityRepository.save(f3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addEmployees() {
        EmployeeRepository employeeRepository = Repositories.getInstance().employeeRepository();

        try {
            Employee emp1 = employeeRepository.createEmployee(
                    "Afonso",
                    "afonso@upskill.pt",
                    "Nurse",
                    "914527836"
            );
            //emp1.setId(1);

            Employee emp2 = employeeRepository.createEmployee(
                    "Carla",
                    "carla@upskill.pt",
                    "Receptionist",
                    "917856342"
            );
            //emp2.setId(2);

            Employee emp3 = employeeRepository.createEmployee(
                    "Sofia",
                    "sofia@upskill.pt",
                    "Nurse",
                    "912346578"
            );
            //emp3.setId(3);

            employeeRepository.save(emp1);
            employeeRepository.save(emp2);
            employeeRepository.save(emp3);

            employeeRepository.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addMenus() {
        //This is where we define the menu structure and respective permissions.
        RoleRepository roleRepository = Repositories.getInstance().roleRepository();

        Menu menu;

        Drawable drawer = new MenuDrawer();
        Context.getInstance().setDrawer(drawer);

        Menu mainMenu = new Menu(null, 1,"Main Menu", null);
        Context.getInstance().setMainMenu(mainMenu);

        //Everyone
        Menu menuLogin = new Menu(Context.getInstance().mainMenu(), 1,"Login", new LoginUI());
        menu = new Menu(Context.getInstance().mainMenu(), 2,"Logout", new LogoutUI());
        menu = new Menu(Context.getInstance().mainMenu(), 9,"About", new AboutUI());

        //Admin
        //menu = new Menu(menuLogin, 1, "Register User", new CreateUserUI());
        //menu.addPermission((Role) roleRepository.roleByName(ROLE_ADMIN));
        menu = new Menu(menuLogin, 2, "Register Vaccine Technology", new RegisterVaccineTechUI());
        menu.addPermission((Role) roleRepository.roleByName(ROLE_ADMIN));
        menu = new Menu(menuLogin, 3, "Register Vaccine Type", new RegisterVaccineTypeUI());
        menu.addPermission((Role) roleRepository.roleByName(ROLE_ADMIN));
        menu = new Menu(menuLogin, 4, "Register Brand", new RegisterBrandUI());
        menu.addPermission((Role) roleRepository.roleByName(ROLE_ADMIN));
        menu = new Menu(menuLogin, 5, "Register Vaccine", new RegisterVaccineUI());
        menu.addPermission((Role) roleRepository.roleByName(ROLE_ADMIN));
        menu = new Menu(menuLogin, 6, "Register Employee", new RegisterEmployeeUI());
        menu.addPermission((Role) roleRepository.roleByName(ROLE_ADMIN));
        //menu = new Menu(menuLogin, 11, "List Employees With Role", new ListEmployeesWithRoleUI());
        //menu.addPermission((Role) roleRepository.roleByName(ROLE_ADMIN));
        menu = new Menu(menuLogin, 12, "List Vaccine Technologies", new ListVaccineTechsUI());
        menu.addPermission((Role) roleRepository.roleByName(ROLE_ADMIN));
        menu = new Menu(menuLogin, 13, "List Vaccine Types", new ListVaccineTypesUI());
        menu.addPermission((Role) roleRepository.roleByName(ROLE_ADMIN));
        menu = new Menu(menuLogin, 14, "List Vaccines", new ListVaccinesUI());
        menu.addPermission((Role) roleRepository.roleByName(ROLE_ADMIN));
        menu = new Menu(menuLogin, 16, "List Employees With Role", new ListEmployeesWithRoleUI());
        menu.addPermission((Role) roleRepository.roleByName(ROLE_ADMIN));

        //Receptionist
        menu = new Menu(menuLogin, 1, "Register SNS User", new RegisterUserUI());
        menu.addPermission((Role) roleRepository.roleByName(ROLE_RECEPTIONIST));
        menu = new Menu(menuLogin, 2, "Schedule Vaccination", new ScheduleVaccinationOnBehalfOfUserUI());
        menu.addPermission((Role) roleRepository.roleByName(ROLE_RECEPTIONIST));
        //menu = new Menu(menuLogin, 3, "Register SNS User Arrival", new RegisterUserArrivalUI());
        //menu.addPermission((Role) roleRepository.roleByName(ROLE_RECEPTIONIST));

        //SNS USer
        //menu = new Menu(menuLogin, 1, "Schedule Vaccination", new ScheduleVaccinationUI());
        //menu.addPermission((Role) roleRepository.roleByName(ROLE_RECEPTIONIST));

        //Nurse
        //menu = new Menu(menuLogin, 1, "List User Waiting", new ListUsersWaitingUI());
        //menu.addPermission((Role) roleRepository.roleByName(ROLE_NURSE));
    }
}