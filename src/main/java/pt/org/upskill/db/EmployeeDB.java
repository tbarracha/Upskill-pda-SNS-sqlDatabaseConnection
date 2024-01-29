package pt.org.upskill.db;

import pt.org.upskill.auth.Email;
import pt.org.upskill.domain.Employee;
import pt.org.upskill.domain.Facility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
SQL Code:
CREATE TABLE Employee (
    id INT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255)
);
*/

public class EmployeeDB implements PersistableObject<Employee> {

    @Override
    public boolean save(Connection connection, Employee object) {

        // 1) encontrar o Employee com o "id = ?"
        String sqlCmd = "select * from Employee where email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sqlCmd)) {
            //if (object.getId() != null)
                ps.setString(1, object.getEmail().address());

            try (ResultSet rs = ps.executeQuery()) {

                // 2) se for encontrado, dar update
                if (rs.next()) {
                    sqlCmd = "update Employee set name = ?, position = ?, phone = ? where email = ?";
                } else {

                    // 3) se for novo, inserir na base de dados
                    // mudar este SQL command para inserir os valores nas colunas
                    sqlCmd = "insert into Employee(email, name, position, phone) values (?, ?, ?, ?)";
                }

                try (PreparedStatement ps2 = connection.prepareStatement(sqlCmd)) {

                    // valores e os seus tipos a inserir nas colunas do sqlCmd anterior
                    ps2.setString(1, object.getEmail().address());
                    ps2.setString(2, object.getName());
                    ps2.setString(3, object.getPosition());
                    ps2.setString(4, object.getPhone());

                    // se não executar este método, #rip info na base de dados
                    // depois falta dar o commit
                    ps2.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDB.class.getName()).log(Level.SEVERE, null, ex);
            return true;
        }
    }

    @Override
    public boolean delete(Connection connection, Employee object) {
        try {
            String sqlCmd;
            sqlCmd = "delete from Employee where email = ?";
            try (PreparedStatement ps = connection.prepareStatement(sqlCmd)) {
                ps.setString(1, object.getEmail().address());

                ps.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Employee getById(Connection connection, int id) {
        try {
            String sqlCmd;
            sqlCmd = "select * from Employee where id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sqlCmd)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Employee employee = null;
                    try {
                        employee = new Employee(
                                new Email(rs.getString("email")),
                                rs.getString("name"),
                                rs.getString("position"),
                                rs.getString("phone")
                        );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return employee;
                }
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
