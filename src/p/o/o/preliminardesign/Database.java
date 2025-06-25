package p.o.o.preliminardesign;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database{
    private static final String URL = "jdbc:mysql://localhost:3306/Staam";
    private static final String USER = "chuyi";
    private static final String PASSWORD = "chuyito05";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}