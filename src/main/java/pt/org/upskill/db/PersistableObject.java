package pt.org.upskill.db;

import java.sql.Connection;

public interface PersistableObject<P> {
    public boolean save(Connection connection, P object);
    public boolean delete(Connection connection, P object);
    public P getById(Connection connection, int id);
}
