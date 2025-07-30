/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p.o.o.preliminardesign;

import javax.swing.JLabel;

/**
 *
 * @author chuyi
 */
public class timerThread extends Thread {
    private int currSeconds = 0; 
    JLabel timerLabel;
    
    private volatile boolean running = true;

    public void stopThread() {
        running = false;
    }
    
    public timerThread(JLabel timerLabel){
        this.timerLabel = timerLabel;
    }
    
    public int getSeconds(){
        return this.currSeconds;
    }
    
    public static String convertSecondsToHMS(int seconds){
        int Minutes = seconds / 60;
        int Hours = Minutes / 60;
        int remainderSeconds = (int)(seconds % 60);
         int remainderMinutes = (int)(Minutes % 60) ;
        String hoursString = (Hours >= 10) ? ""+Hours : "0"+Hours;
        String minutesString = (remainderMinutes >= 10) ? ""+remainderMinutes : "0"+remainderMinutes;
        String secondsString = (remainderSeconds >= 10) ? ""+remainderSeconds:"0"+remainderSeconds;
        return hoursString+":" +minutesString+":"+secondsString;
    }
    
    /**
     *
     */
    @Override
    public void run() {
        while(true){
            try {
                this.timerLabel.setText(timerThread.convertSecondsToHMS(this.currSeconds));
                this.currSeconds+= Database.getOneSecondEqualsTo();
                Thread.sleep((long) 1000.00);
            } catch (InterruptedException ex) {
                System.getLogger(timerThread.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }
}
