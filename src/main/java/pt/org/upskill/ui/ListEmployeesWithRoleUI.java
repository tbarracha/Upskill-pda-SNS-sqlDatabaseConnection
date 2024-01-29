package pt.org.upskill.ui;

import pt.org.upskill.controller.EmployeeController;
import pt.org.upskill.dto.KeyValueDTO;

import java.util.List;

import static pt.org.upskill.ui.utils.Utils.readLineFromConsole;

public class ListEmployeesWithRoleUI extends UI {

    private final EmployeeController employeeController = new EmployeeController();

    public void run() {
        System.out.println("");
        System.out.println("LIST EMPLOYEES WITH ROLE");
        System.out.println("-----------");

        try {
            // Prompt the user to enter the role or filter criteria
            String role = readLineFromConsole("Enter the role to filter employees: ");

            // Use the EmployeeController to get a list of employees with the specified role
            List<KeyValueDTO> employeesWithRole = employeeController.getEmployeesWithRole(role);

            if (employeesWithRole.isEmpty()) {
                System.out.println("No employees found with the specified role.");
            } else {
                System.out.println("Employees with the specified role:");
                for (KeyValueDTO employee : employeesWithRole) {
                    System.out.println(employee.key + " - " + employee.value);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
