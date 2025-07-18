/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p.o.o.preliminardesign;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author chuyi
 */
public class windowCreator {
    public static void openWindow(String title, int width, int height, JPanel panel) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(title);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(width, height);
            frame.setLocationRelativeTo(null);
            frame.setContentPane(panel);
            frame.setVisible(true);
            frame.setResizable(false);
        });
    }
    
    public static void openJframeWindow(JFrame window , String title){
        window.setVisible(true);
        window.setResizable(false);
        window.setTitle(title);  
    }
    
    public static void setIconLabel(JLabel label , byte[] imageBytes){
        ImageIcon icon = new ImageIcon(imageBytes);
        Image image = icon.getImage().getScaledInstance(label.getWidth(),label.getHeight(), Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(image));
    }
}
