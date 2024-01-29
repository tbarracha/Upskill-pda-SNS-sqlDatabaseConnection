package pt.org.upskill.ui;

import pt.org.upskill.controller.EmployeeController;
import pt.org.upskill.dto.DTO;
import pt.org.upskill.dto.EmployeeDTO;

import static pt.org.upskill.ui.utils.Utils.readLineFromConsole;

public class RegisterEmployeeUI extends UI {

    private final EmployeeController employeeController = new EmployeeController();

    public void run() {
        System.out.println("");
        System.out.println("REGISTER EMPLOYEE");
        System.out.println("-----------");

        try {
            String email = readLineFromConsole("Email Address: ");
            String name = readLineFromConsole("Name: ");
            String position = readLineFromConsole("Position: ");
            String phone = readLineFromConsole("Phone: ");

            // Create an EmployeeDTO
            DTO dto = new EmployeeDTO(email, name, position, phone);

            // Register the employee using the controller
            employeeController.register(dto);

            // Save the employee
            if (employeeController.save()) {
                System.out.println("Employee registered successfully.");
            } else {
                System.out.println("Failed to register the employee.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
