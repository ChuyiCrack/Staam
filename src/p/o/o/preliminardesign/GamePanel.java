
package p.o.o.preliminardesign;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author chuyi
 */
public class GamePanel extends JPanel{
        public GamePanel(Game game) {
            setLayout(new GridLayout(1, 4, 10, 10)); // vertical layout
            setBorder(BorderFactory.createLineBorder(Color.GRAY));
            //setAlignmentY(TOP_ALIGNMENT);
            ImageIcon icon = new ImageIcon(game.getImage());
            JLabel gameImage = new JLabel("");
           JButton viewGame = new JButton("View Game");
           viewGame.setPreferredSize(new Dimension(100, 30));
            Image image = icon.getImage().getScaledInstance(100  ,100, Image.SCALE_SMOOTH);
            gameImage.setIcon(new ImageIcon(image));
            JLabel titleLabel = new JLabel(game.Name);
            titleLabel.setAlignmentY(TOP_ALIGNMENT);
            JLabel priceLabel = new JLabel("Price: $" + game.Price);
            priceLabel.setAlignmentY(TOP_ALIGNMENT);
            titleLabel.setBackground(Color.red);
            priceLabel.setBackground(Color.blue);
            add(gameImage);
            add(titleLabel);
            add(priceLabel);
            add(viewGame);
            
            viewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //Agregar la ventana especifica del Juego
               
            }
        });
            
            //setPreferredSize(new Dimension(200, 100));
        }
}
