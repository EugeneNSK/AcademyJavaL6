package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class H2BaseConnectPrepared {

    public static Connection getH2Connection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:mydatabase", "user1", "password1");
        return connection;
    }
}
