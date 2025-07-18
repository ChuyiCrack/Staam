/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package p.o.o.preliminardesign;

import Views.Tienda;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 *
 * @author chuyi
 */
public class infoGame extends javax.swing.JFrame {
    int gameID;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(infoGame.class.getName());

    /**
     * Creates new form infoGame
     * @param gameID
     */
    public infoGame(JFrame previousWindow,int gameID) {
        initComponents();
        previousWindow.dispose();
        boxAddCartButton.setLayout(new FlowLayout());
        String query = "SELECT * FROM Juegos AS J INNER JOIN Publisher AS P ON J.ID_PUBLISHER = P.ID WHERE J.ID_JUEGOS = ?";
        User currUser = (User) SessionManager.getCurrentUser();
        this.gameID=gameID;
        try{
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,gameID);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            String gameNameString = rs.getString("J.Nombre");
            String gameDescriptionString = rs.getString("J.Descripcion");
            
            windowCreator.setIconLabel(coverGame,  rs.getBytes("J.fotoPortada"));
            
            windowCreator.setIconLabel(backgroundImage, rs.getBytes("J.fotoBackground"));
            
            double gamePrice = rs.getDouble("J.Precio");
            String releaseDateText = rs.getDate("J.FLanzamiento").toString();
            String OS = rs.getString("J.sistemaOperativo");
            String CPU = rs.getString("J.requirimientoRam") ;
            String Ram = rs.getString("J.requirimientoProcesador") ;
            String GPU = rs.getString("J.requirimientoGrafica") ;
          
            priceLabel.setText("$"+ gamePrice);
            gameName.setText(gameNameString);
            descriptionGame.setText(gameDescriptionString);
            releaseDate.setText(releaseDateText);
            priceLabel.setText(gamePrice == 0 ? "FREE" : "$"+gamePrice);
            
            recomendedOS.setText(OS);
            recomendedCPU.setText(CPU);
            recomendedRAM.setText(Ram + " Gb");
            recomendedGPU.setText(GPU);
            
            String publisherNameString = rs.getString("P.Nombre");
            publisherName.setText(publisherNameString);
            windowCreator.setIconLabel(publisherLogo, rs.getBytes("P.Imagen"));
            
            query = "SELECT * FROM Carrito WHERE ID_Usuario = ? AND ID_Juego = ? ";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, SessionManager.getCurrentUser().getID());
            stmt.setInt(2, gameID);
            rs = stmt.executeQuery();
            if(rs.next()){
                JLabel exist = new JLabel("Already in the cart");
                exist.setVerticalAlignment(SwingConstants.CENTER);
                exist.setForeground(Color.white);
                boxAddCartButton.setBackground(Color.red);
                boxAddCartButton.add(exist, BorderLayout.CENTER);
            }
            else{
                
            JButton addCart = new JButton("Add to Cart");
            
            addCart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String query = "INSERT INTO Carrito(ID_Juego , ID_Usuario) VALUES(?, ?)";

                    try {
                        Connection conn = Database.getConnection();
                        PreparedStatement stmt = conn.prepareStatement(query);
                        stmt.setInt(1, gameID);
                        stmt.setInt(2, SessionManager.getCurrentUser().getID());
                        stmt.executeUpdate();
                        stmt.close(); // Good practice to close PreparedStatement
                    } catch (SQLException ex) {
                         JOptionPane.showMessageDialog(null , "Error: " + e );
                    }
                    Window window = SwingUtilities.getWindowAncestor((Component) e.getSource());
                    window.dispose();
                    windowCreator.openJframeWindow(new Tienda(), "Store");
                }
            });
            
                boxAddCartButton.add(addCart);
                boxAddCartButton.revalidate();
                boxAddCartButton.repaint();
            }
       
           
            
        }
        catch (SQLException e) {
             JOptionPane.showMessageDialog(null , "Error with SQL: " + e );
        } 
    }
    
    public infoGame(){
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        calificationLabel = new javax.swing.JPanel();
        gameName = new javax.swing.JLabel();
        backgroundImage = new javax.swing.JLabel();
        coverGame = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionGame = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        releaseDate = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        recomendedGPU = new javax.swing.JLabel();
        recomendedCPU = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        recomendedOS = new javax.swing.JLabel();
        recomendedRAM = new javax.swing.JLabel();
        getBack = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        publisherLogo = new javax.swing.JLabel();
        publisherName = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        priceLabel = new javax.swing.JLabel();
        boxAddCartButton = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        calificationLabel.setBackground(new java.awt.Color(1, 44, 98));
        calificationLabel.setForeground(new java.awt.Color(255, 255, 255));

        gameName.setFont(new java.awt.Font("Adwaita Sans", 0, 48)); // NOI18N
        gameName.setForeground(new java.awt.Color(255, 255, 255));
        gameName.setText("Game Name");

        backgroundImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        coverGame.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        descriptionGame.setBackground(new java.awt.Color(1, 44, 98));
        descriptionGame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        descriptionGame.setForeground(new java.awt.Color(255, 255, 255));
        descriptionGame.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        jScrollPane1.setViewportView(descriptionGame);

        jLabel1.setFont(new java.awt.Font("Adwaita Sans", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Release Date: ");

        jLabel2.setFont(new java.awt.Font("Adwaita Sans", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Calification : ");

        releaseDate.setFont(new java.awt.Font("Adwaita Sans", 1, 14)); // NOI18N
        releaseDate.setForeground(new java.awt.Color(255, 255, 255));
        releaseDate.setText("jLabel4");

        jPanel2.setBackground(new java.awt.Color(1, 44, 98));

        recomendedGPU.setFont(new java.awt.Font("Adwaita Sans", 1, 18)); // NOI18N
        recomendedGPU.setForeground(java.awt.Color.white);
        recomendedGPU.setText("jLabel9");

        recomendedCPU.setFont(new java.awt.Font("Adwaita Sans", 1, 18)); // NOI18N
        recomendedCPU.setForeground(new java.awt.Color(255, 255, 255));
        recomendedCPU.setText("jLabel9");

        jLabel4.setFont(new java.awt.Font("Adwaita Sans", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("System Requirements");

        jLabel3.setFont(new java.awt.Font("Adwaita Sans", 1, 18)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("CPU:");

        jLabel6.setFont(new java.awt.Font("Adwaita Sans", 1, 18)); // NOI18N
        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setText("OS: ");

        jLabel7.setFont(new java.awt.Font("Adwaita Sans", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("RAM: ");

        jLabel8.setFont(new java.awt.Font("Adwaita Sans", 1, 18)); // NOI18N
        jLabel8.setForeground(java.awt.Color.white);
        jLabel8.setText("GPU:");

        recomendedOS.setFont(new java.awt.Font("Adwaita Sans", 1, 18)); // NOI18N
        recomendedOS.setForeground(java.awt.Color.white);
        recomendedOS.setText("jLabel9");

        recomendedRAM.setFont(new java.awt.Font("Adwaita Sans", 1, 18)); // NOI18N
        recomendedRAM.setForeground(java.awt.Color.white);
        recomendedRAM.setText("jLabel9");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(recomendedRAM, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(recomendedOS, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(recomendedCPU, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(recomendedGPU, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel4)
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(recomendedOS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(recomendedRAM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(recomendedGPU))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(recomendedCPU)
                    .addComponent(jLabel7))
                .addGap(10, 10, 10))
        );

        getBack.setBackground(new java.awt.Color(1, 44, 98));
        getBack.setForeground(new java.awt.Color(255, 255, 255));
        getBack.setText("Get Back");
        getBack.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        getBack.setBorderPainted(false);
        getBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getBackActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(1, 44, 98));

        jLabel9.setFont(new java.awt.Font("Adwaita Sans", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Publisher");

        publisherLogo.setForeground(new java.awt.Color(255, 255, 255));
        publisherLogo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));

        publisherName.setFont(new java.awt.Font("Adwaita Sans", 1, 14)); // NOI18N
        publisherName.setForeground(new java.awt.Color(255, 255, 255));
        publisherName.setText("Publisher Name");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(publisherLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(publisherName))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(publisherLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(publisherName)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(1, 44, 98));

        priceLabel.setFont(new java.awt.Font("Adwaita Sans", 1, 18)); // NOI18N
        priceLabel.setForeground(new java.awt.Color(255, 255, 255));
        priceLabel.setText("$$$$");

        boxAddCartButton.setBackground(new java.awt.Color(1, 44, 98));

        javax.swing.GroupLayout boxAddCartButtonLayout = new javax.swing.GroupLayout(boxAddCartButton);
        boxAddCartButton.setLayout(boxAddCartButtonLayout);
        boxAddCartButtonLayout.setHorizontalGroup(
            boxAddCartButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        boxAddCartButtonLayout.setVerticalGroup(
            boxAddCartButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(priceLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxAddCartButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(priceLabel)
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(boxAddCartButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout calificationLabelLayout = new javax.swing.GroupLayout(calificationLabel);
        calificationLabel.setLayout(calificationLabelLayout);
        calificationLabelLayout.setHorizontalGroup(
            calificationLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(calificationLabelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(calificationLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(calificationLabelLayout.createSequentialGroup()
                        .addGroup(calificationLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(getBack)
                            .addGroup(calificationLabelLayout.createSequentialGroup()
                                .addComponent(coverGame, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(calificationLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(backgroundImage, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addGroup(calificationLabelLayout.createSequentialGroup()
                                .addGroup(calificationLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(calificationLabelLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(99, 99, 99)
                                        .addComponent(jLabel1))
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(calificationLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(calificationLabelLayout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(calificationLabelLayout.createSequentialGroup()
                                        .addComponent(releaseDate)
                                        .addGap(86, 86, 86)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(69, 69, 69))
                    .addGroup(calificationLabelLayout.createSequentialGroup()
                        .addComponent(gameName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        calificationLabelLayout.setVerticalGroup(
            calificationLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(calificationLabelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(getBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gameName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(calificationLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(calificationLabelLayout.createSequentialGroup()
                        .addComponent(backgroundImage, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(coverGame, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(calificationLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(calificationLabelLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(calificationLabelLayout.createSequentialGroup()
                        .addGroup(calificationLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(calificationLabelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(releaseDate))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(calificationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(calificationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void getBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getBackActionPerformed
        this.dispose();
        windowCreator.openJframeWindow(new Tienda() , "Store");
    }//GEN-LAST:event_getBackActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new infoGame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundImage;
    private javax.swing.JPanel boxAddCartButton;
    private javax.swing.JPanel calificationLabel;
    private javax.swing.JLabel coverGame;
    private javax.swing.JTextPane descriptionGame;
    private javax.swing.JLabel gameName;
    private javax.swing.JButton getBack;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JLabel publisherLogo;
    private javax.swing.JLabel publisherName;
    private javax.swing.JLabel recomendedCPU;
    private javax.swing.JLabel recomendedGPU;
    private javax.swing.JLabel recomendedOS;
    private javax.swing.JLabel recomendedRAM;
    private javax.swing.JLabel releaseDate;
    // End of variables declaration//GEN-END:variables
}
