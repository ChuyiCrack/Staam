package p.o.o.preliminardesign;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class Database{
    private static final String URL = "jdbc:mysql://localhost:3306/Staam";
    private static final String USER = "chuyi";
    private static final String PASSWORD = "chuyito05";
    private static String pathDefaultProfileUserImage = "/home/chuyi/Documents/Java Projects/Staam/src/Media/defaultUser.png";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public static String hashPasswords(String Password){
        return BCrypt.hashpw(Password, BCrypt.gensalt());
    }
     
    //Toma la contrasena sin hash, la hashea  y la compara con el hash para saber si son iguales.
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
    
    public static boolean checkEmail(String email){
        return (email.contains("@") && email.contains("."));
    }
    
    public static boolean checkValidUsername(String user){
        return (!(user.contains("@") || user.contains(".")));
        }
    public static String getPathDefaultProfileUserImage(){
        return Database.pathDefaultProfileUserImage;
    }
}