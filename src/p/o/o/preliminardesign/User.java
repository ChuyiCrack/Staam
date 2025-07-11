
package p.o.o.preliminardesign;
import javax.swing.ImageIcon;

public class User extends GlobalUser{
    double Balance;
    String Telefono;
    ImageIcon profilePicture;
    
    public User(int ID, String Email, String Name,double Balance , String FechaCreacion , String Ubicacion , String Telefono , ImageIcon profilePicture){
        super(ID, Email, Name , Ubicacion,FechaCreacion);
        this.Type = "User";
        this.Balance = Balance;
        this.Telefono = Telefono;
        this.profilePicture=profilePicture;
    }
    
    public double getBalance() {
        return this.Balance;
    }

    public String getTelefono() {
        return this.Telefono;
    } 
    public ImageIcon getProfilePicture(){
        return this.profilePicture;
    }
    
    public void setBalance(double balance){
        this.Balance = balance;
    }
}
