package pt.org.upskill.controller;

import pt.org.upskill.auth.Email;
import pt.org.upskill.domain.Employee;
import pt.org.upskill.dto.DTO;
import pt.org.upskill.dto.EmployeeDTO;
import pt.org.upskill.dto.KeyValueDTO;
import pt.org.upskill.repository.EmployeeRepository;
import pt.org.upskill.repository.Repositories;

import java.util.ArrayList;
import java.util.List;

public class EmployeeController implements UIable {

    private EmployeeRepository employeeRepository = Repositories.getInstance().employeeRepository();

    private Employee employee;

    @Override
    public void register(DTO dto) {
        EmployeeDTO employeeDTO = (EmployeeDTO) dto;
        try {
            employee = new Employee(
                    new Email(employeeDTO.getEmail()),
                    employeeDTO.getName(),
                    employeeDTO.getPosition(),
                    employeeDTO.getPhone()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean save() {
        if (employee != null) {
            return employeeRepository.save(employee);
        }
        return false;
    }

    @Override
    public List<KeyValueDTO> keyValueDTOList() {
        // You can implement this method based on your EmployeeRepository's logic
        // For example, create KeyValueDTOs for employees and return a list of them
        List<Employee> employees = employeeRepository.employeeList();
        List<KeyValueDTO> dtoList = new ArrayList<>();
        for (Employee emp : employees) {
            dtoList.add(new KeyValueDTO(emp.getEmail().address(), emp.getName()));
        }
        return dtoList;
    }

    public List<KeyValueDTO> getEmployeesWithRole(String role) {
        //List<Employee> employees = employeeRepository.employeeList();
        List<Employee> employees = employeeRepository.getAll();
        List<KeyValueDTO> employeesWithRole = new ArrayList<>();

        for (Employee emp : employees) {
            if (emp.getPosition() != null && emp.getPosition().equalsIgnoreCase(role)) {
                employeesWithRole.add(new KeyValueDTO(emp.getEmail().address(), emp.getName()));
            }
        }

        return employeesWithRole;
    }
}
