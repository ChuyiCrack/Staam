/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import java.sql.Statement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import p.o.o.preliminardesign.Database;
import p.o.o.preliminardesign.SessionManager;
import p.o.o.preliminardesign.updatePanel;
import p.o.o.preliminardesign.windowCreator;

/**
 *
 * @author irvin
 */
public class GameBInfo extends javax.swing.JFrame {
    /**
     * Creates new form UsProf
     * @param idGame
     */
    private boolean shouldOpenNextWindow = true;
    int idGame;
    String gameNameGlobal;
    public void doNotOpenNextWIndow(){
        this.shouldOpenNextWindow = false;
    }
    
    public GameBInfo(int idGame){
        initComponents();
        this.idGame = idGame;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (shouldOpenNextWindow){
                    windowCreator.openJframeWindow(new Biblioteca(), "Library");
                }
            }
        });
         String query = "SELECT * FROM juegosBiblioteca AS JB INNER JOIN Juegos AS J ON JB.ID_Juego = J.ID_Juegos WHERE JB.ID_Juego = ? AND JB.ID_Usuario = ?";
         GameBInfo actualWindow = this;
         try{
             Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             stmt.setInt(1, idGame);
             stmt.setInt(2, SessionManager.getCurrentUser().getID());
             ResultSet rs = stmt.executeQuery();
             rs.next();
             String gameName = rs.getString("J.Nombre");
             this.gameNameGlobal = gameName;
             windowCreator.setIconLabel(LblPortrait, rs.getBytes("J.fotoBackground"));
             timePlayed.setText("Mssing");
             sizeGame.setText(Database.convertMBtoGB(rs.getInt("J.pesoJuego")));
            Dimension buttonSize = new Dimension(100, 50);
            // Step 1: Create the inner panel
            buttonPanel.setLayout(new BorderLayout());
            if (rs.getBoolean("JB.instalado")) {
                JButton playButton = new JButton("Play");
                playButton.setBackground(Color.decode("#08FF25"));
                playButton.setForeground(Color.white);
                playButton.setPreferredSize(buttonSize);
                buttonPanel.add(playButton, BorderLayout.CENTER);
                playButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            try{
                                PreparedStatement stmt = conn.prepareStatement("INSERT INTO SesionJuego(ID_User,ID_Juego) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
                                stmt.setInt(1, SessionManager.getCurrentUser().getID());
                                stmt.setInt(2, idGame);
                                stmt.executeUpdate();
                                ResultSet rs = stmt.getGeneratedKeys();
                                if(rs.next()){
                                    int idSession = rs.getInt(1);
                                    actualWindow.doNotOpenNextWIndow();
                                    actualWindow.dispose();
                                    windowCreator.openJframeWindow(new gamePlay(idGame , idSession,comboBoxUpdate.getSelectedItem().toString()), gameName.replace(" ", "_") +".exe");
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "The seccion wasnt created");
                                }
                            }
                            catch (SQLException err) {
                                JOptionPane.showMessageDialog(null, "Error(PlayGame): " + err.getMessage());
                             }    
                    }
                });
            } else {
                JButton installButton = new JButton("Install");
                installButton.setForeground(Color.white);
                installButton.setBackground(Color.decode("#2724FF"));
                installButton.setPreferredSize(buttonSize);
                buttonPanel.add(installButton, BorderLayout.CENTER);
                installButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                            actualWindow.doNotOpenNextWIndow();
                            actualWindow.dispose();
                            windowCreator.openJframeWindow(new installingGame(idGame), "Installing "+gameName);
                       

                    }
                });
                settingsPanel.setVisible(false);
            }
            
            buttonPanel.revalidate();
            buttonPanel.repaint();
            

            // Step 4: Refresh
            buttonPanel.revalidate();
            buttonPanel.repaint();
                 
             placeHolder.setLayout(new BorderLayout());

            JPanel itemsPanel = new JPanel();
            itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));

            JScrollPane scrollPane = new JScrollPane(itemsPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
               scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
               placeHolder.setPreferredSize(new Dimension(400, 200));
            placeHolder.add(scrollPane, BorderLayout.CENTER);
         
         
            stmt = conn.prepareStatement("SELECT * FROM SesionJuego WHERE ID_User = ? AND ID_Juego = ? ORDER BY InicioSesion DESC LIMIT 1");
            stmt.setInt(1, SessionManager.getCurrentUser().getID());
            stmt.setInt(2, idGame);
             rs = stmt.executeQuery();
             if(rs.next()){
                 lastSession.setText(rs.getString("InicioSesion"));
                 stmt = conn.prepareStatement("SELECT obtenerTiempoJugado(?,?) AS playedTime");
                stmt.setInt(1, idGame);
                stmt.setInt(2, SessionManager.getCurrentUser().getID());
                rs = stmt.executeQuery();
                rs.next();
                int minutes = rs.getInt("playedTime");
                timePlayed.setText(Database.getTimePlayed(minutes));
             }
             else{
                 sesionPanel.setVisible(false);
                 lastTimePanel.setVisible(false);
             }
             
              stmt = conn.prepareStatement("SELECT * FROM ResJuegos WHERE ID_Usuario = ? AND ID_Juego = ?;");
              stmt.setInt(1, SessionManager.getCurrentUser().getID());
              stmt.setInt(2, this.idGame);
               rs = stmt.executeQuery();
               if(rs.next()){
                   reviewGame.setVisible(false);
                   boolean liked= rs.getBoolean("Cali");
                   if(liked){
                        opinionGame.setText("Good");
                        opinionGame.setForeground(Color.GREEN);
                    }
                    else{
                        opinionGame.setText("Bad");
                        opinionGame.setForeground(Color.red);
                    }
               }
               else{
                      jLabel9.setVisible(false);
                      opinionGame.setVisible(false);
                      editReview.setVisible(false);
                      jLabel8.setText("You haven't done a review");
               }
               stmt = conn.prepareStatement("SELECT * FROM Updates WHERE ID_juego = ? ORDER BY FechaUp DESC");
               stmt.setInt(1, this.idGame);
                rs = stmt.executeQuery();
                int sizeUpdates = 0;
                while(rs.next()){
                    sizeUpdates++;
                    updatePanel updtPanel = new updatePanel(rs.getString("Titulo" ) , rs.getString("Descripcion") , rs.getString("VersionJuego") ,rs.getBytes("fotoPortada"));
                    itemsPanel.add(updtPanel);
                    comboBoxUpdate.addItem(rs.getString("VersionJuego"));
                }
                comboBoxUpdate.addItem("Beta");
                itemsPanel.setPreferredSize(new Dimension(380, 100 * sizeUpdates));
         }
          catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
         }
         
         placeHolder.repaint();
         placeHolder.revalidate();
    }
    public GameBInfo() {
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

        PnlGlobal = new javax.swing.JPanel();
        PnlInfo = new javax.swing.JPanel();
        LblInfo = new javax.swing.JLabel();
        placeHolder = new javax.swing.JPanel(new BorderLayout());
        jPanel1 = new javax.swing.JPanel();
        LblPlaTime = new javax.swing.JLabel();
        sizeGame = new javax.swing.JLabel();
        sesionPanel = new javax.swing.JPanel();
        LblPlaTime1 = new javax.swing.JLabel();
        lastSession = new javax.swing.JLabel();
        lastTimePanel = new javax.swing.JPanel();
        LblPlaTime3 = new javax.swing.JLabel();
        timePlayed = new javax.swing.JLabel();
        buttonPanel = new javax.swing.JPanel();
        settingsPanel = new javax.swing.JPanel();
        uninstallButton = new javax.swing.JButton();
        comboBoxUpdate = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        PnlFriends2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        opinionGame = new javax.swing.JLabel();
        editReview = new javax.swing.JButton();
        reviewGame = new javax.swing.JButton();
        LblPortrait = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PnlGlobal.setBackground(new java.awt.Color(102, 102, 102));

        PnlInfo.setBackground(new java.awt.Color(153, 153, 153));

        LblInfo.setForeground(new java.awt.Color(51, 51, 255));
        LblInfo.setText("Latest Content                                                                            ");

        javax.swing.GroupLayout placeHolderLayout = new javax.swing.GroupLayout(placeHolder);
        placeHolder.setLayout(placeHolderLayout);
        placeHolderLayout.setHorizontalGroup(
            placeHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        placeHolderLayout.setVerticalGroup(
            placeHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 173, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PnlInfoLayout = new javax.swing.GroupLayout(PnlInfo);
        PnlInfo.setLayout(PnlInfoLayout);
        PnlInfoLayout.setHorizontalGroup(
            PnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlInfoLayout.createSequentialGroup()
                .addGroup(PnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(placeHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE))
                .addContainerGap())
        );
        PnlInfoLayout.setVerticalGroup(
            PnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlInfoLayout.createSequentialGroup()
                .addComponent(LblInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(placeHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        LblPlaTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblPlaTime.setText("Size Game:");

        sizeGame.setFont(new java.awt.Font("Adwaita Sans", 1, 14)); // NOI18N
        sizeGame.setForeground(new java.awt.Color(255, 255, 255));
        sizeGame.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sizeGame.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblPlaTime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sizeGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(LblPlaTime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sizeGame)
                .addContainerGap())
        );

        sesionPanel.setBackground(new java.awt.Color(102, 102, 102));

        LblPlaTime1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblPlaTime1.setText("Last Session:");

        lastSession.setFont(new java.awt.Font("Adwaita Sans", 1, 14)); // NOI18N
        lastSession.setForeground(new java.awt.Color(255, 255, 255));
        lastSession.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastSession.setText("jLabel1");

        javax.swing.GroupLayout sesionPanelLayout = new javax.swing.GroupLayout(sesionPanel);
        sesionPanel.setLayout(sesionPanelLayout);
        sesionPanelLayout.setHorizontalGroup(
            sesionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sesionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sesionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lastSession, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblPlaTime1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                .addContainerGap())
        );
        sesionPanelLayout.setVerticalGroup(
            sesionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sesionPanelLayout.createSequentialGroup()
                .addComponent(LblPlaTime1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lastSession)
                .addContainerGap())
        );

        lastTimePanel.setBackground(new java.awt.Color(102, 102, 102));

        LblPlaTime3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblPlaTime3.setText("Time Played:");

        timePlayed.setFont(new java.awt.Font("Adwaita Sans", 1, 14)); // NOI18N
        timePlayed.setForeground(new java.awt.Color(255, 255, 255));
        timePlayed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timePlayed.setText("jLabel1");

        javax.swing.GroupLayout lastTimePanelLayout = new javax.swing.GroupLayout(lastTimePanel);
        lastTimePanel.setLayout(lastTimePanelLayout);
        lastTimePanelLayout.setHorizontalGroup(
            lastTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblPlaTime3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
            .addGroup(lastTimePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(timePlayed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        lastTimePanelLayout.setVerticalGroup(
            lastTimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lastTimePanelLayout.createSequentialGroup()
                .addComponent(LblPlaTime3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timePlayed)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonPanel.setBackground(new java.awt.Color(102, 102, 102));
        buttonPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
        );

        settingsPanel.setBackground(new java.awt.Color(102, 102, 102));

        uninstallButton.setBackground(new java.awt.Color(255, 51, 51));
        uninstallButton.setForeground(new java.awt.Color(255, 255, 255));
        uninstallButton.setText("Uninstall");
        uninstallButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uninstallButtonActionPerformed(evt);
            }
        });

        comboBoxUpdate.setFont(new java.awt.Font("Adwaita Sans", 1, 13)); // NOI18N
        comboBoxUpdate.setForeground(new java.awt.Color(0, 0, 0));
        comboBoxUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxUpdateActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Adwaita Sans", 1, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Game Version");

        javax.swing.GroupLayout settingsPanelLayout = new javax.swing.GroupLayout(settingsPanel);
        settingsPanel.setLayout(settingsPanelLayout);
        settingsPanelLayout.setHorizontalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(uninstallButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboBoxUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        settingsPanelLayout.setVerticalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, settingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uninstallButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        PnlFriends2.setBackground(new java.awt.Color(102, 102, 102));

        jLabel8.setFont(new java.awt.Font("Adwaita Sans", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Review Game");

        jLabel9.setFont(new java.awt.Font("Adwaita Sans", 3, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("You think this game is");

        opinionGame.setFont(new java.awt.Font("Adwaita Sans", 3, 14)); // NOI18N
        opinionGame.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        opinionGame.setText("Review");

        editReview.setBackground(new java.awt.Color(255, 102, 0));
        editReview.setForeground(new java.awt.Color(255, 255, 255));
        editReview.setText("Edit Review");
        editReview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PnlFriends2Layout = new javax.swing.GroupLayout(PnlFriends2);
        PnlFriends2.setLayout(PnlFriends2Layout);
        PnlFriends2Layout.setHorizontalGroup(
            PnlFriends2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(opinionGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlFriends2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(editReview)
                .addGap(33, 33, 33))
        );
        PnlFriends2Layout.setVerticalGroup(
            PnlFriends2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlFriends2Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(opinionGame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editReview)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        reviewGame.setBackground(new java.awt.Color(0, 0, 255));
        reviewGame.setForeground(new java.awt.Color(255, 255, 255));
        reviewGame.setText("Make a Review");
        reviewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reviewGameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PnlFriends2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(reviewGame)
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(PnlFriends2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(reviewGame)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PnlGlobalLayout = new javax.swing.GroupLayout(PnlGlobal);
        PnlGlobal.setLayout(PnlGlobalLayout);
        PnlGlobalLayout.setHorizontalGroup(
            PnlGlobalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlGlobalLayout.createSequentialGroup()
                .addComponent(PnlInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(PnlGlobalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sesionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lastTimePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(settingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );
        PnlGlobalLayout.setVerticalGroup(
            PnlGlobalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlGlobalLayout.createSequentialGroup()
                .addGroup(PnlGlobalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlGlobalLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PnlGlobalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sesionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lastTimePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(settingsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(PnlGlobalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PnlInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        LblPortrait.setText("Inserte Caratula Aqui*");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblPortrait, javax.swing.GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE)
            .addComponent(PnlGlobal, javax.swing.GroupLayout.PREFERRED_SIZE, 805, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(LblPortrait, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PnlGlobal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void uninstallButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uninstallButtonActionPerformed
        String query = "UPDATE juegosBiblioteca SET instalado = FALSE WHERE ID_Juego = ? AND ID_Usuario = ?";
        try{
            Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             stmt.setInt(1, this.idGame);
             stmt.setInt(2, SessionManager.getCurrentUser().getID());
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
         }
        this.doNotOpenNextWIndow();
        this.dispose();
         windowCreator.openJframeWindow(new GameBInfo(this.idGame), this.gameNameGlobal);
    }//GEN-LAST:event_uninstallButtonActionPerformed

    private void comboBoxUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxUpdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxUpdateActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        this.doNotOpenNextWIndow();
        windowCreator.openJframeWindow(new Calificacion(this.idGame,true), "reviewing " + this.gameNameGlobal);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void reviewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reviewGameActionPerformed
        this.dispose();
        this.doNotOpenNextWIndow();
        windowCreator.openJframeWindow(new Calificacion(this.idGame,false), "reviewing " + this.gameNameGlobal);
    }//GEN-LAST:event_reviewGameActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameBInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameBInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameBInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameBInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameBInfo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblInfo;
    private javax.swing.JLabel LblPlaTime;
    private javax.swing.JLabel LblPlaTime1;
    private javax.swing.JLabel LblPlaTime3;
    private javax.swing.JLabel LblPortrait;
    private javax.swing.JPanel PnlFriends2;
    private javax.swing.JPanel PnlGlobal;
    private javax.swing.JPanel PnlInfo;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JComboBox<String> comboBoxUpdate;
    private javax.swing.JButton editReview;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lastSession;
    private javax.swing.JPanel lastTimePanel;
    private javax.swing.JLabel opinionGame;
    private javax.swing.JPanel placeHolder;
    private javax.swing.JButton reviewGame;
    private javax.swing.JPanel sesionPanel;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JLabel sizeGame;
    private javax.swing.JLabel timePlayed;
    private javax.swing.JButton uninstallButton;
    // End of variables declaration//GEN-END:variables
}
