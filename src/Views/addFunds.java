
package Views;


import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import javax.swing.JFrame;
import p.o.o.preliminardesign.Database;
import p.o.o.preliminardesign.SessionManager;
import p.o.o.preliminardesign.User;









public class addFunds extends javax.swing.JFrame {
   
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(addFunds.class.getName());
    
    
    public addFunds() {
        initComponents();
        this.setLocationRelativeTo(null);
    }


  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        JTNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        JTCVV = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        JTNumTarjeta = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        JTDinero = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        CBMes = new javax.swing.JComboBox<>();
        CBAnio = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        JTApellido = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(1, 44, 98));

        jLabel6.setFont(new java.awt.Font("Adwaita Sans", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Titular Information");

        jLabel1.setFont(new java.awt.Font("Adwaita Sans", 1, 14)); // NOI18N
        jLabel1.setText("CVV");

        JTNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JTNombre.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        JTNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTNombreActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Adwaita Sans", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Name");

        jLabel3.setFont(new java.awt.Font("Adwaita Sans", 1, 14)); // NOI18N
        jLabel3.setText("Expiration Date");

        JTCVV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JTCVV.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        JTCVV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTCVVActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Adwaita Sans", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Card NUmber");

        JTNumTarjeta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JTNumTarjeta.setForeground(new java.awt.Color(153, 153, 153));
        JTNumTarjeta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        JTNumTarjeta.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                JTNumTarjetaAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        JTNumTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTNumTarjetaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Add Funds");

        JTDinero.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JTDinero.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        JTDinero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTDineroActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Adwaita Sans", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Funds to add");

        CBMes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CBMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        CBMes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        CBMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBMesActionPerformed(evt);
            }
        });

        CBAnio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CBAnio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050" }));
        CBAnio.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        CBAnio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBAnioActionPerformed(evt);
            }
        });

        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Add Funds");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Adwaita Sans", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Last Name");

        JTApellido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JTApellido.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        JTApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTApellidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(237, 237, 237)
                        .addComponent(jLabel3)
                        .addGap(107, 107, 107)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(JTNumTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110)
                        .addComponent(CBMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(CBAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(JTCVV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(110, 110, 110)
                        .addComponent(jLabel8)
                        .addGap(129, 129, 129)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(JTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(JTApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(JTDinero, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JTNumTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTCVV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(jLabel6)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8)
                    .addComponent(jLabel5))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JTNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTDinero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JTNumTarjetaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_JTNumTarjetaAncestorAdded
        
    }//GEN-LAST:event_JTNumTarjetaAncestorAdded

    private void JTNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTNombreActionPerformed
        
    }//GEN-LAST:event_JTNombreActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
           
            String nombre = JTNombre.getText();
              if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "The name field is empty");
                    return;
            }
              if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")) {
                JOptionPane.showMessageDialog(null, "Invalid Character in field name");
              
                return;
            } 
            String apellido = JTApellido.getText();  
              if (apellido.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Last Name field is empty.");
                    return;
            }
              if (!apellido.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")) {
                JOptionPane.showMessageDialog(null, "INvalid character in Last Name field");
              
                return;
            } 
            String numtarjeta = JTNumTarjeta.getText();
            
              if (numtarjeta.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "The number card field is empty");
                    return;
            }
              if (!numtarjeta.matches("\\d{16}")) {
                JOptionPane.showMessageDialog(null, "Invalid Card Number."
                 + "Should contain exactly 16 numbers");
                return;
            } 
              
            String depositotexto = JTDinero.getText();
            
              if (depositotexto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "The amount is empty.");
                    return;
            }
              if (!depositotexto.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "The amount need to be a valid number");
                return;
            }
            int deposito = Integer.parseInt(depositotexto);
   
            String codigo = JTCVV.getText();
              if (codigo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "The CVV field is empty");
                    return;
            }
              if (!codigo.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "The CVV needs to be a valid number");
                return;
            }
            String mes = CBMes.getSelectedItem().toString();
            String anio = CBAnio.getSelectedItem().toString();
            String fechaCaducidad = mes + "/" + anio;

        


    try {
        java.sql.Connection conn = Database.getConnection();
        String sqlTransaccion = "INSERT INTO  transaccionesFondos (Num_Tarjeta, Total, ID_Usuario) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sqlTransaccion);
        stmt.setString(1, numtarjeta);   
        stmt.setDouble(2, deposito);
        stmt.setInt(3, SessionManager.getCurrentUser().getID());
        stmt.executeUpdate();
        
        String sqlUsuario = "UPDATE Usuarios SET Balance = Balance + ? WHERE ID = ?";
        PreparedStatement stmt2 = conn.prepareStatement(sqlUsuario);
        stmt2.setDouble(1, deposito);
        stmt2.setInt(2, SessionManager.getCurrentUser().getID());
        stmt2.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "The transaction was succesfully completed");
        this.dispose();
        User currUser = (User) (SessionManager.getCurrentUser());
        currUser.setBalance(currUser.getBalance() + deposito);
        JFrame newWIndow = new Tienda();
        newWIndow.setVisible(true);
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al guardar: " + e.getMessage());
    }



   
    }//GEN-LAST:event_jButton1ActionPerformed

    private void JTNumTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTNumTarjetaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTNumTarjetaActionPerformed

    private void JTDineroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTDineroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTDineroActionPerformed

    private void CBMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBMesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBMesActionPerformed

    private void CBAnioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBAnioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBAnioActionPerformed

    private void JTCVVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTCVVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTCVVActionPerformed

    private void JTApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTApellidoActionPerformed

  
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
        java.awt.EventQueue.invokeLater(() -> new addFunds().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBAnio;
    private javax.swing.JComboBox<String> CBMes;
    private javax.swing.JTextField JTApellido;
    private javax.swing.JTextField JTCVV;
    private javax.swing.JTextField JTDinero;
    private javax.swing.JTextField JTNombre;
    private javax.swing.JTextField JTNumTarjeta;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
