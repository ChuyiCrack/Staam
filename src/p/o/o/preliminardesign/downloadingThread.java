
package p.o.o.preliminardesign;

import Views.GameBInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class downloadingThread extends Thread{
    int targetMB;
    JLabel displayLabel;
    JLabel timeLeft;
    String targetDisplayed;
    JFrame currWindow;
    int idGame;
    String gameName;
    private volatile boolean running = true;

    public void stopThread() {
        running = false;
    }
    public downloadingThread(int targetMB , JLabel displayLabel , JLabel timeLeft ,int idGame ,String gameName  ,JFrame currWindow){
        this.targetMB = targetMB;
        this.displayLabel = displayLabel;
        this.timeLeft = timeLeft;
        this.targetDisplayed = Database.convertMBtoGB(targetMB);
        this.currWindow = currWindow;
        this.idGame = idGame;
        this.gameName = gameName;
        
    }
    @Override
    public void run() {
        int currMB = 0;
        
        while (currMB <= this.targetMB){
            currMB+= Database.getDownloadSpeed();
            String toDisplay = Database.convertMBtoGB(currMB) + " / " + this.targetDisplayed;
            this.displayLabel.setText(toDisplay);
            this.timeLeft.setText(Database.getTimeLeftDownload(this.targetMB-currMB));
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            System.getLogger(downloadingThread.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
            
        }
        String query = "Update juegosBiblioteca set instalado = true WHERE ID_Juego = ? AND ID_Usuario = ?";
        try{
                Connection conn = Database.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, this.idGame);
                stmt.setInt(2, SessionManager.getCurrentUser().getID());
                stmt.executeUpdate();
        }
       catch (SQLException e) {
                    JOptionPane.showMessageDialog(null , "Error: " + e.getMessage());
                }
         JOptionPane.showMessageDialog(null , "The download has been completed");
         this.currWindow.dispose();
         windowCreator.openJframeWindow(new GameBInfo(this.idGame), this.gameName);
  }
}
