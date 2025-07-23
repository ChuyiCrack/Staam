
package p.o.o.preliminardesign;

import Views.Carrito;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class cartGamePanel extends JPanel {
    public cartGamePanel(Carrito carritoView , int idCarrito,String gameNameString , double price , JPanel gamesContainer , JLabel sizeCartLabel , JLabel subTotalLabel , JPanel paymentContainer){
        setLayout(new GridLayout(1, 3, 10, 10)); 
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JLabel gameName = new JLabel(gameNameString);
         JLabel gamePrice = new JLabel("Price: $"+price);
         JButton deleteGame = new JButton("Remove");
         this.setPreferredSize(new Dimension(400, 100)); // adjust height as needed
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); // Let it expand in width
         add(gameName);
         add(gamePrice);
         add(deleteGame);
         
         deleteGame.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
             gamesContainer.removeAll();
            String query = "DELETE FROM Carrito WHERE ID_Carrito = ?";
            try {
                Connection conn = Database.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, idCarrito);
                stmt.executeUpdate();
                double subTotal = CarritoClase.showALlCartGames(carritoView,gamesContainer, paymentContainer, sizeCartLabel, subTotalLabel );
                carritoView.setSubTotal(subTotal);
                stmt.close();
                conn.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    });
               
    }
}
