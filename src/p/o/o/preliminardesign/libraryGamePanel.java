
package p.o.o.preliminardesign;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class libraryGamePanel extends JPanel {
    public libraryGamePanel (Game game){
         setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(150, 180));
        setMaximumSize(new Dimension(150, 180));
        
        
        // Title label (centered)
        JLabel gameName = new JLabel(game.Name);
        gameName.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameName.setFont(new Font("Arial", Font.BOLD, 14));
        gameName.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Add some spacing

        // Game image
        ImageIcon icon = new ImageIcon(game.getImage());
        Image image = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        JLabel gameImage = new JLabel(new ImageIcon(image));
        gameImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        JLabel extraInfo = new JLabel(game.extraLibrarySection);
        extraInfo .setAlignmentX(Component.CENTER_ALIGNMENT);
        extraInfo .setFont(new Font("Arial", Font.BOLD, 12));
        extraInfo.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Add some spacing

        // Add components
        add(gameName);
        add(gameImage);
        add(extraInfo);
    }
}
