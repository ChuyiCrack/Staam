package p.o.o.preliminardesign;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
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
    
    public static String convertMBtoGB(int MB){
        if(MB < 1024){
            return MB + " MB";
        }
        double GB =Math.round(((double) MB / 1024) * 100.0) / 100.00;
        return GB + " GB";
        
    }
    
    public static String getGameCalification(int idGame){
        String query = "SELECT obtenerPorcentajeAprovacion(?) AS porcentageAproved";
        double  porcentageAproved;
        try{
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, idGame);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            porcentageAproved = rs.getDouble("porcentageAproved");
            System.out.println(porcentageAproved);
           
        }
        catch (SQLException e) {
             JOptionPane.showMessageDialog(null , "Error with SQL: " + e );
             return "Error";
            }
        if(porcentageAproved == -1){
            return "No reviews Yet";
        }
        if(porcentageAproved >= .45 &&  porcentageAproved <= .55 ){
            return "Mid";
        }
        
        if(porcentageAproved > .55){
            return  porcentageAproved >= .85 ?"Insane Good": "Good";
        }
        return porcentageAproved <= .15 ? "Shit" : "Bad";
    }
    
    public static Color getColorCalification(String opinion){
            Color resColor;
            resColor = switch (opinion) {
            case "Mid" -> Color.decode("#FFE208");
            case "Insane Good" -> Color.decode("#60FF21");
            case "Good" -> Color.decode("#16E512");
            case "Shit" -> Color.decode("#E51D12");
            case "Bad" -> Color.decode("#E55F12");
            default -> Color.decode("#FFF");
        };
            return resColor;
    }
}