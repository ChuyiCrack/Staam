
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

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
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
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 238, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 39, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 508, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("INFORMACION DEL TITULAR");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("CVV");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, -1, -1));

        JTNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JTNombre.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        JTNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTNombreActionPerformed(evt);
            }
        });
        getContentPane().add(JTNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 120, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Nombre ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Fecha de caducidad");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, -1, -1));

        JTCVV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JTCVV.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        JTCVV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTCVVActionPerformed(evt);
            }
        });
        getContentPane().add(JTCVV, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 90, 50, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Numero de tarjeta");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

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
        getContentPane().add(JTNumTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 220, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("TRANSACCION");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 6, -1, -1));

        JTDinero.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JTDinero.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        JTDinero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTDineroActionPerformed(evt);
            }
        });
        getContentPane().add(JTDinero, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, 130, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Cantidad a depositar");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, -1, -1));

        CBMes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CBMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        CBMes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        CBMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBMesActionPerformed(evt);
            }
        });
        getContentPane().add(CBMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, -1, -1));

        CBAnio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CBAnio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045", "2046", "2047", "2048", "2049", "2050" }));
        CBAnio.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        CBAnio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBAnioActionPerformed(evt);
            }
        });
        getContentPane().add(CBAnio, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, -1, -1));

        jButton1.setText("Listo");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 220, 70, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Apellido");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, -1, -1));

        JTApellido.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        JTApellido.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        JTApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTApellidoActionPerformed(evt);
            }
        });
        getContentPane().add(JTApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 220, 120, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JTNumTarjetaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_JTNumTarjetaAncestorAdded
        
    }//GEN-LAST:event_JTNumTarjetaAncestorAdded

    private void JTNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTNombreActionPerformed
        
    }//GEN-LAST:event_JTNombreActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
           
            String nombre = JTNombre.getText();
              if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El campo de nombre está vacío.");
                    return;
            }
              if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")) {
                JOptionPane.showMessageDialog(null, "caracter inválido en el nombre.");
              
                return;
            } 
            String apellido = JTApellido.getText();  
              if (apellido.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El campo de apellido está vacío.");
                    return;
            }
              if (!apellido.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")) {
                JOptionPane.showMessageDialog(null, "caracter inválido en el apellido.");
              
                return;
            } 
            String numtarjeta = JTNumTarjeta.getText();
            
              if (numtarjeta.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El campo de numero de tarjeta está vacío.");
                    return;
            }
              if (!numtarjeta.matches("\\d{16}")) {
                JOptionPane.showMessageDialog(null, "Número de tarjeta inválido."
                 + " Debe contener exactamente 16 dígitos o carecter invalido.");
                return;
            } 
              
            String depositotexto = JTDinero.getText();
            
              if (depositotexto.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El campo de dinero está vacío.");
                    return;
            }
              if (!depositotexto.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "El depósito debe ser un número válido.");
                return;
            }
            int deposito = Integer.parseInt(depositotexto);
   
            String codigo = JTCVV.getText();
              if (codigo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El campo de CVV está vacío.");
                    return;
            }
              if (!codigo.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "El CVV debe ser un número válido.");
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
