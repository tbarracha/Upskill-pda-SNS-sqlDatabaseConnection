package pt.org.upskill.db;

import pt.org.upskill.auth.Email;
import pt.org.upskill.domain.Address;
import pt.org.upskill.domain.Facility;
import pt.org.upskill.domain.Phone;
import pt.org.upskill.domain.Website;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FacilityDB implements PersistableObject<Facility> {

    @Override
    public boolean save(Connection connection, Facility object) {
        String sqlCmd;
        sqlCmd = "select * from Facility where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sqlCmd)) {
            ps.setInt(1, object.id());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    sqlCmd = "update Facility set name = ? where id = ?";
                }
                else {
                    sqlCmd = "insert into Facility(name, id) values (?, ?)";
                }
                //
                try (PreparedStatement ps2 = connection.prepareStatement(sqlCmd)) {
                    ps2.setString(1, object.name());
                    ps2.setInt(2, object.id());
                    ps2.executeUpdate();
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacilityDB.class.getName()).log(Level.SEVERE, null, ex);
            return true;
        }
    }

    @Override
    public boolean delete(Connection connection, Facility object) {
        try {
            String sqlCmd;
            sqlCmd = "delete from Facility where id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sqlCmd)) {
                ps.setInt(1, object.id());
                ps.executeUpdate();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacilityDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Facility getById(Connection connection, int id) {
        try {
            String sqlCmd;
            sqlCmd = "select * from Facility where id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sqlCmd)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    Facility facility = null;
                    try {
                        facility = new Facility.Builder()
                                .withId(rs.getInt("id"))
                                .withName(rs.getString("name"))
                                .withAddress(new Address(
                                        rs.getString("address_streetname"),
                                        rs.getString("address_postalcode"),
                                        rs.getString("addrress_cityname")))
                                .withEmail(new Email(rs.getString("email")))
                                .withPhone(new Phone(rs.getString("phone")))
                                .withFax(new Phone(rs.getString("fax")))
                                .withWebsite(new Website(rs.getString("website")))
                                //.withOpeningHour(rs.getTime("openinghour"))
                                //.withClosingingHour(rs.getTime("closinghour"))
                                .withMaxVaccinesPerHour(rs.getInt("maxvaccinesperhour"))
                                .build();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return facility;
                }
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacilityDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
