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
    
    private static final Double downloadSpeed = 2500.00; 
    private static final int oneSecondsEqualTo = 250; 

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
    
    public static String[] validMediaExtensions(){
        String[] supportedExtensions = {".png", ".jpg", ".jpeg", ".gif", ".bmp"};
        return supportedExtensions;
    }
    
    public static double getDownloadSpeed(){
        return Database.downloadSpeed;
    }
    
    public static int getOneSecondEqualsTo(){
        return Database.oneSecondsEqualTo;
    }
    public static String convertMBtoGB(int MB){
        if(MB < 1024){
            return MB + " MB";
        }
        double GB =Math.round(((double) MB / 1024) * 100.0) / 100.00;
        return GB + " GB";
        
    }
    
    public static String getTimeLeftDownload(int mbLeft){
        int SecondsLeft = (int) (mbLeft / Database.getDownloadSpeed());
        return Database.getTimeLeft(SecondsLeft);
    }
    
    public static String getTimeLeft(int SecondsLeft){
        if(SecondsLeft  < 60){
            return SecondsLeft + " s";
        }
        int minutesLeft = (SecondsLeft / 60);
        SecondsLeft = (int)( SecondsLeft % 60);
        if(minutesLeft  < 60){
            return minutesLeft + " m, " + (SecondsLeft) + " s";
        }
        int Hours = (minutesLeft/60);
        return Hours + " h , " + (((int) minutesLeft % 60)) + " m ," + SecondsLeft + " s" ;
    }
    
    public static String getTimePlayed(int minutes){
        if(minutes < 100){
            return minutes+" minutes";
        }
        //double rounded = Math.round(number * 100.0) / 100.0;
        double hours =  (double) minutes / 60;
        System.out.println(hours);
        double hoursRounded = Math.round(hours * 100.0) / 100.0;
        return hoursRounded + " hours";
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