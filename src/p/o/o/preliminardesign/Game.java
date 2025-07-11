package p.o.o.preliminardesign;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Game {
    int ID;
    String Name;
    byte[] imageBytes;
    double Price;
    
    
    public Game(int ID , String Name){
        this.ID = ID;
        this.Name = Name;
    }
    
    public Game(int ID , String Name ,  byte[] imageBytes , double Price){
        this.ID = ID;
        this.Name = Name;
        this.imageBytes = imageBytes;
        this.Price = Price;
    }
    
    public byte[] getImage(){
        return this.imageBytes;
    }
            
    @Override
    public String toString() {
        return this.ID + "-" + this.Name; 
    }
    
    public static ArrayList<Game > getAllGames(){
        ArrayList<Game > games = new ArrayList<>();
        String query = "SELECT * FROM Juegos";
        try{
             Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery();
             while(rs.next()){
                 int ID = rs.getInt("ID_Juegos");
                 String name = rs.getString("Nombre") ;
                 double price = rs.getDouble("Precio");
                 byte[] image = rs.getBytes("fotoPortada");
                 Game game = new Game(ID , name , image, price);
                 games.add(game);
             }   
        }
         catch (SQLException e){
             System.out.println("Error: " + e);
         }
        return games;
    }
}
