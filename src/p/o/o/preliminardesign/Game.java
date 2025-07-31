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
    String extraLibrarySection = null;
    
    
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
    
    public Game(int ID , String Name ,  byte[] imageBytes , double Price ,  String extraLibrarySection){
        this.ID = ID;
        this.Name = Name;
        this.imageBytes = imageBytes;
        this.Price = Price;
        this.extraLibrarySection =  extraLibrarySection;
    }
    
    public byte[] getImage(){
        return this.imageBytes;
    }
            
    @Override
    public String toString() {
        return this.ID + "-" + this.Name; 
    }
    
    public static ArrayList<Game > getAllGames(String filterBy , String orderBY ,String gameName){
        ArrayList<Game > games = new ArrayList<>();
        String query;
        gameName = (gameName.equals("")) ? "'%'" : "'%" + gameName + "%'" ;
        if("Price".equals(filterBy)){
            query = "SELECT * FROM Juegos WHERE Nombre LIKE " + gameName + " ORDER BY Precio "+ orderBY;
        }
        else if("Rating".equals(filterBy)){
            query = "SELECT *,obtenerPorcentajeAprovacion(ID_Juegos) AS Calificacion FROM Juegos WHERE Nombre LIKE " + gameName + " ORDER BY Precio "+ orderBY;
        }
        else{
             query = "SELECT * FROM Juegos WHERE Nombre LIKE " + gameName + " ORDER BY Calificacion "+ orderBY;
        }
        
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
    public static ArrayList<Game > getAllLibraryGames(int idUser,String filterBy , String orderBY ,String gameName){
         ArrayList<Game > games = new ArrayList<>();
         String filteredColumn = switch (filterBy) {
            case "PlayTime" -> "TiempoJugado";
            case "ReleaseDate" -> "J.FLanzamiento";
            case "SizeGame" -> "J.pesoJuego";
            default -> "J.Nombre";
        };
         gameName = (gameName.equals("") ||gameName.equals("Search a Game") ) ? "'%'" : "'%" + gameName + "%'" ;
         String query = "SELECT *,obtenerTiempoJugado(JB.ID_Juego,JB.ID_Usuario) AS TiempoJugado FROM juegosBiblioteca AS JB INNER JOIN Juegos AS J ON JB.ID_Juego = J.ID_Juegos WHERE ID_Usuario = ? AND J.Nombre LIKE "+gameName+" ORDER BY " +filteredColumn+ " "+orderBY;
         try{
             Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             stmt.setInt(1, idUser);
             ResultSet rs = stmt.executeQuery();
             String extra;
             while(rs.next()){
                 int ID = rs.getInt("J.ID_Juegos");
                 String name = rs.getString("J.Nombre") ;
                 double price = rs.getDouble("J.Precio");
                 byte[] image = rs.getBytes("J.fotoPortada");
                 if(filteredColumn.equals("J.Nombre")){
                     extra = "";
                 }
                 else if(filteredColumn.equals("TiempoJugado")){
                     extra = Database.getTimePlayed(rs.getInt("TiempoJugado"));
                 }
                 else if(filteredColumn.equals("J.pesoJuego")){
                     extra = Database.convertMBtoGB(rs.getInt("J.pesoJuego"));
                 }
                 else{
                     extra = rs.getString(filteredColumn);
                 }
                 Game game = new Game(ID , name , image, price,extra);
                 games.add(game);
             }   
        }
         catch (SQLException e){
             System.out.println("Error: " + e);
         }
         return games;
    }
}
