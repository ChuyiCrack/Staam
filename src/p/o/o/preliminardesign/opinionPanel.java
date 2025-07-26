
package p.o.o.preliminardesign;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class opinionPanel extends JPanel {
    public opinionPanel(String userName , boolean liked , String Opinion , byte[] profilePicture){
        setLayout(new BorderLayout(10, 10)); // spacing
        setBorder(BorderFactory.createLineBorder(Color.BLACK)); // outer border
        setBackground(Color.decode("#022E66"));
        // Top panel containing: [La] [Username] [La]
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.decode("#01234D"));

        JLabel userProfile = new JLabel("", SwingConstants.CENTER);
        userProfile.setForeground(Color.white);
        JLabel centerLabel = new JLabel(userName, SwingConstants.CENTER);
        centerLabel.setForeground(Color.white);
        JLabel rightLabel = new JLabel("", SwingConstants.CENTER);
        
        if(liked){
            rightLabel.setText("Liked");
            rightLabel.setForeground(Color.GREEN);
        }
        else{
            rightLabel.setText("Not Liked");
            rightLabel.setForeground(Color.red);
        }
        
        

        // Optional: Fix size to make labels look like squares
        Dimension labelSize = new Dimension(40, 40);
        userProfile.setPreferredSize(labelSize);
        userProfile.setSize(labelSize);
        userProfile.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        topPanel.add(userProfile, BorderLayout.WEST);
        topPanel.add(centerLabel, BorderLayout.CENTER);
        topPanel.add(rightLabel, BorderLayout.EAST);

        // Text field in center
        JTextField textField = new JTextField(Opinion);
        textField.setHorizontalAlignment(JTextField.CENTER);
        JPanel fieldPanel = new JPanel(new BorderLayout());
        fieldPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        fieldPanel.add(textField, BorderLayout.CENTER);
        // Add to main panel
        add(topPanel, BorderLayout.NORTH);
        add(fieldPanel, BorderLayout.CENTER);
        windowCreator.setIconLabel(userProfile, profilePicture);
    }
}
