package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private static final String url = "jdbc:mysql://localhost:3306/ESS_Trading";
    private static String user = "root";
    private static String password = "Mysql123.";

    public static Connection connect() {
        try {
            // opening database connection to MySQL server
            if(System.getProperty("user.name").equals("gcamaz")) // verify user
            {
                user = "gcamaz";
                password = "";
            }

            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);

        } catch (SQLException | ClassNotFoundException sqlEx) {
            sqlEx.printStackTrace();
        }
        return null;
    }

    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (Exception e) {}
    }
}
