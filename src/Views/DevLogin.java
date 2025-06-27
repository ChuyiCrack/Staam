
package Views;

import java.awt.Window;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import p.o.o.preliminardesign.Database;
import p.o.o.preliminardesign.Publisher;
import p.o.o.preliminardesign.SessionManager;
import p.o.o.preliminardesign.windowCreator;

public class DevLogin extends javax.swing.JPanel {

    public DevLogin() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        LblTitle = new javax.swing.JLabel();
        LblUser = new javax.swing.JLabel();
        Email = new javax.swing.JTextField();
        LblPassword = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        login = new javax.swing.JButton();
        LblIcon = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        createDevAccount = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(1, 44, 98));

        LblTitle.setBackground(new java.awt.Color(0, 0, 153));
        LblTitle.setFont(new java.awt.Font("Yu Gothic UI", 1, 24)); // NOI18N
        LblTitle.setForeground(new java.awt.Color(255, 255, 255));
        LblTitle.setText("STAAM");

        LblUser.setForeground(new java.awt.Color(255, 255, 255));
        LblUser.setText("Sign in with Email");

        Email.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailActionPerformed(evt);
            }
        });

        LblPassword.setForeground(new java.awt.Color(255, 255, 255));
        LblPassword.setText("Password");

        password.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        login.setText("Sign In");
        login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginMouseClicked(evt);
            }
        });
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        LblIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        LblIcon.setPreferredSize(new java.awt.Dimension(512, 512));

        jLabel1.setFont(new java.awt.Font("Adwaita Sans", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("This Login is for Developers");

        createDevAccount.setText("Create Developer Account");
        createDevAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createDevAccountActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(1, 44, 98));
        jButton1.setFont(new java.awt.Font("Adwaita Sans", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Get Back");
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(login)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(createDevAccount))
                            .addComponent(LblPassword, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Email, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(password, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblUser, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(LblTitle)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(LblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LblTitle)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jButton1)))
                .addGap(29, 29, 29)
                .addComponent(LblUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(LblPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(login)
                    .addComponent(createDevAccount))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailActionPerformed

    private void loginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_loginMouseClicked

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        String email , Password;
        email = Email.getText();
        Password = password.getText();
        if(!Database.checkEmail(email)){
             JOptionPane.showMessageDialog(null , "The email " + email + " is not valid" );
            return;
        }
        String query = "SELECT * FROM Publisher where Email = ?";
        try{
                Connection conn = Database.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, email);
                ResultSet rs = stmt.executeQuery();
                if(!rs.next()){
                    JOptionPane.showMessageDialog(null , "An account with email " + email + " doesn't exist" );
                    return;
                }
                if(!Database.checkPassword(Password ,  rs.getString("password_hash"))){
                    JOptionPane.showMessageDialog(null , "Password Incorrect");
                    return;
                }
                Publisher currPublisher = new Publisher(rs.getInt("ID"),rs.getString("Email"),rs.getString("Nombre"), rs.getString("Ubicacion"),rs.getDate("FechaCreacion").toString(),rs.getString("Descripcion"));
                SessionManager.login(currPublisher);
                JFrame newWIndows = new mainViewPublisher();
                newWIndows.setVisible(true);
                Window window = SwingUtilities.getWindowAncestor(this);
                window.dispose();
        
        }
        catch (SQLException e) {
                    e.printStackTrace();
                }
    }//GEN-LAST:event_loginActionPerformed

    private void createDevAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createDevAccountActionPerformed
        windowCreator.openWindow("Create Publisher Account", 970, 600, new DevFormat());
        Window window = SwingUtilities.getWindowAncestor(this);
        window.dispose();
    }//GEN-LAST:event_createDevAccountActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        windowCreator.openWindow("Login", 400, 400, new Login());
        Window window = SwingUtilities.getWindowAncestor(this);
        window.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Email;
    private javax.swing.JLabel LblIcon;
    private javax.swing.JLabel LblPassword;
    private javax.swing.JLabel LblTitle;
    private javax.swing.JLabel LblUser;
    private javax.swing.JButton createDevAccount;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton login;
    private javax.swing.JPasswordField password;
    // End of variables declaration//GEN-END:variables
}
