package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/yourdbname";
    private static final String USER = "root";
    private static final String PASS = "password";

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 8 driver
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
