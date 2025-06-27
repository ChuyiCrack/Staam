
package p.o.o.preliminardesign;

import java.awt.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Publisher extends GlobalUser{
    String Descripcion;
    public Publisher(int ID, String Email, String Name, String Ubicacion,String FechaCreacion,String Descripcion) {
        super(ID, Email, Name ,Ubicacion,FechaCreacion);
        this.Type = "Publisher";
        this.Descripcion = Descripcion;
    }
    
    public String getDescripcion(){
        return this.Descripcion;
    }
    
    public ArrayList<Game> getAllGames() {
        ArrayList<Game> gameList = new ArrayList<>();

        try {
            Connection conn = Database.getConnection();  // Or however you connect
            String query = "SELECT ID_Juegos ,  Nombre FROM Juegos WHERE ID_Publisher = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, SessionManager.getCurrentUser().getID());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Game newGame = new Game(rs.getInt("ID_Juegos"),rs.getString("Nombre"));
                gameList.add(newGame);  // or any column name
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gameList;
    }
}
