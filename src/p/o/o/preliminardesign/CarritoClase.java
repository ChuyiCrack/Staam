/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p.o.o.preliminardesign;

import Views.Carrito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CarritoClase {
    public static double showALlCartGames(Carrito carritoFrame ,JPanel gamesContainer , JPanel paymentContainer , JLabel sizeCartLabel , JLabel subTotal){
         String query = "SELECT * FROM Carrito AS C INNER JOIN Juegos AS J ON C.ID_Juego = J.ID_Juegos WHERE ID_Usuario = ?";
         double subTotalSum = 0.0;
         try{
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, SessionManager.getCurrentUser().getID());
            ResultSet rs = stmt.executeQuery();
            int sizeCart = 0;
            while(rs.next()){
                System.out.println(rs.getString("J.Nombre"));
                sizeCart++;
                double gamePrice = rs.getDouble("J.Precio");
                subTotalSum += gamePrice;
                cartGamePanel newPanel = new cartGamePanel(carritoFrame,rs.getInt("C.ID_Carrito"),rs.getString("J.Nombre") , gamePrice , gamesContainer , sizeCartLabel , subTotal , paymentContainer);
                gamesContainer.add(newPanel);
            }
            if(sizeCart == 0){
                sizeCartLabel.setText("No games in the Cart");
                paymentContainer.setVisible(false);
                return 0;
            }
            String titleSize = sizeCart == 1 ? " Game" : " Games";
            sizeCartLabel.setText( sizeCart + titleSize);
            subTotal.setText("$"+subTotalSum);
    
        }
        catch (SQLException e) {
             JOptionPane.showMessageDialog(null , "Error with SQL: " + e );
        }
        
        
        gamesContainer.repaint();
        gamesContainer.revalidate();
        
        sizeCartLabel.repaint();
       sizeCartLabel.revalidate();
        
         subTotal.repaint();
         subTotal.revalidate();
         return subTotalSum;
}
}
