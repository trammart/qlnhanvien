package helper;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author NgocTram
 */
public class DB_Helper {

    public static Connection openConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String DB_URL = "jdbc:mysql://localhost/ct467";
        String USER_NAME = "root";
        String PASSWORD = "";
        Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        return conn;
    }
}
