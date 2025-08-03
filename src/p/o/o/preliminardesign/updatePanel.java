/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p.o.o.preliminardesign;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.HashSet;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author chuyi
 */
public class updatePanel extends JPanel{
    public updatePanel(String titleText, String contentText ,String versionJuego ,byte[] imageUpdate) {
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        String title = titleText+ "(" + versionJuego +")";
        JTextField titleField = new JTextField(title);
        add(titleField, BorderLayout.NORTH);

        // Picture + Text Field section
        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));

        JLabel pictureLabel = new JLabel("", SwingConstants.CENTER);
        pictureLabel.setPreferredSize(new Dimension(100, 60));
        pictureLabel.setSize(new Dimension(100, 60));
        pictureLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        

         JTextField contentField = new JTextField(contentText);

        centerPanel.add(pictureLabel, BorderLayout.WEST);
        centerPanel.add(contentField, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);
        windowCreator.setIconLabel(pictureLabel, imageUpdate);
    }
}
