package pt.org.upskill.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public interface IRepositoryWithDB<T> {
    Connection getConnection();
    List<T> getAll();
    T buildFromResultSet(ResultSet resultSet);
}
