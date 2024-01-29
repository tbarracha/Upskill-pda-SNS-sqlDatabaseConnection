package pt.org.upskill.repository;

import pt.org.upskill.auth.Email;
import pt.org.upskill.db.ConnectionFactory;
import pt.org.upskill.db.DatabaseConnection;
import pt.org.upskill.db.EmployeeDB;
import pt.org.upskill.domain.Employee;
import pt.org.upskill.dto.KeyValueDTO;
import pt.org.upskill.session.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements PersistableRepo, IRepositoryWithDB<Employee> {

    private Connection conn;
    private List<Employee> employeeList = new ArrayList<>();

    public EmployeeRepository() {}

    private boolean validateSave(Employee employee) {
        // You can implement validation logic specific to saving employees here
        return true;
    }

    private boolean validateDelete(Employee employee) {
        // You can implement validation logic specific to deleting employees here
        return true;
    }

    public Employee createEmployee(String name, String email, String position, String phone) {
        Email employeeEmail;
        try {
            employeeEmail = new Email(email);
        } catch (Exception e) {
            // Handle email validation exception here
            return null;
        }

        Employee employee = new Employee(employeeEmail, name, position, phone);
        // You can set other properties of the employee here
        return employee;
    }

    @Override
    public boolean save(Object object) {
        if (object instanceof Employee employee) {
            // Update the database using EmployeeDB
            if (conn == null)
                conn = getConnection();

            try {
                conn.setAutoCommit(false);
                EmployeeDB employeeDB = new EmployeeDB();
                employeeDB.save(conn, employee);
                conn.commit();
                return true;
            } catch (SQLException e) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return false;
    }

    public void commit() {
        if (conn == null)
            conn = getConnection();

        try {
            conn.commit();
            System.out.println("Commited employees!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Object object) {
        if (object instanceof Employee) {
            Employee employee = (Employee) object;
            if (validateDelete(employee)) {
                // Delete from the database using EmployeeDB
                if (conn == null)
                    conn = getConnection();

                try {
                    conn.setAutoCommit(false);
                    EmployeeDB employeeDB = new EmployeeDB();
                    employeeDB.delete(conn, employee);
                    conn.commit();
                    return true;
                } catch (SQLException e) {
                    try {
                        conn.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    public List<Employee> employeeList() {
        return employeeList;
    }

    public Employee getByEmail(String email) {
        for (Employee employee : employeeList) {
            if (employee.hasEmail(email)) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public Connection getConnection() {
        ConnectionFactory cf = Context.getConnectionFactory();
        DatabaseConnection dbc = cf.getDatabaseConnection();
        return dbc.getConnection();
    }

    @Override
    public List<Employee> getAll() {
        if (conn == null)
            conn = getConnection();

        try {
            List<Employee> list = new ArrayList<>();
            String sqlCmd = "SELECT * FROM Employee";
            try (PreparedStatement ps = conn.prepareStatement(sqlCmd)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    list.add(buildFromResultSet(rs));
                }
                return list;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee buildFromResultSet(ResultSet resultSet) {
        try {
            Email email = new Email(resultSet.getString("email"));
            String name = resultSet.getString("name");
            String position = resultSet.getString("position"); // Extract the 'position' column
            String phone = resultSet.getString("phone"); // Extract the 'phone' column

            // You can add other columns as needed

            // Create and return an Employee instance with the extracted information
            return new Employee(email, name, position, phone);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<KeyValueDTO> keyValueDTOList() {
        List<KeyValueDTO> dtoList = new ArrayList<>();
        //for (Employee item : employeeList()) {
        for (Employee item : getAll()) {
            dtoList.add(new KeyValueDTO(item.getEmail().address(), item.getName()));
        }

        return dtoList;
    }
}
